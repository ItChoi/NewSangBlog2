package com.blog.newsangblog2.web.uploader.service;

import com.blog.newsangblog2.common.enumeration.FileRoute;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.common.utils.FileUtils;
import com.blog.newsangblog2.common.utils.UserUtils;
import com.blog.newsangblog2.web.manager.portfolio.repository.PortfolioRepository;
import com.blog.newsangblog2.web.manager.portfolio.service.PortfolioService;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.service.ManagerUserService;
import com.blog.newsangblog2.web.uploader.s3.S3Uploader;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ArrayUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;


@Slf4j
@RequiredArgsConstructor
@Service
public class UploadService {

    private final PortfolioService portfolioService;

    private final PortfolioRepository portfolioRepository;

    private final ManagerUserService managerUserService;

    private final S3Uploader s3Uploader;


    public Map<Object, Object> settingFroalaImageResponse(MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        File uploadImage = settingtFileDir();
        Map<Object, Object> responseData = settingResponseData(file, response, request);

        return responseData;
    }

    private File settingtFileDir() {
        String loginId = UserUtils.getLoginId();

        if (StringUtils.isEmpty(loginId)) {
            throw new UserNotFoundException("로그인 상태가 아닙니다.");
        }

        Manager manager = managerUserService.findManagerBy(loginId).orElseThrow(() -> new UserNotFoundException(loginId));

        /*int portfolioMaxOrdering = */
        Integer portfolioMaxOrdering = portfolioRepository.getMaxOrderingByManagerId(manager.getId());
        int newOrdering = portfolioMaxOrdering == null ? 1 : portfolioMaxOrdering + 1;

        // 포트폴리오 저장 경로: portfolio/loginId/MaxOrdering/파일이름
        String fileDir = FileRoute.PORTFOLIO + File.separator + loginId + File.separator + newOrdering + File.separator;
        return new File(fileDir);
    }

    private Map<Object, Object> settingResponseData(MultipartFile file, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        Map<Object, Object> responseData = new HashMap<>();;
        File uploadImageSrc = settingtFileDir();

        Part filePart = request.getPart("file");
        String linkName = null;
        String fileName = null;
        final PrintWriter writer = response.getWriter();

        try {
            String contentTypeInfo = request.getContentType();
            nullCheckContentType(contentTypeInfo, "multipart/form-data");
            String absoluteServerPath = uploadImageSrc + settingFullFileName(file.getOriginalFilename());

            /*String path = request.getHeader("referer");
            linkName = path + ""*/
            linkName = s3Uploader.getS3FileUrl(absoluteServerPath);

            File fileInfo = new File(linkName);
            if (!ArrayUtils.contains(FileUtils.ALLOWED_EXTS, FilenameUtils.getExtension(absoluteServerPath))
            || !ArrayUtils.contains(FileUtils.ALLOWED_MIMETYPE, filePart.getContentType().toLowerCase())) {

                /*File fileInfo = new File(absoluteServerPath);*/
                fileInfo = new File(linkName);
                if (fileInfo.exists()) {
                    fileInfo.delete();
                }

                throw new Exception("Image does not meet the validation.");
            }
            
            // 디렉토리 + 파일명
            //File fileInfo = new File(absoluteServerPath);
            fileInfo = new File(linkName);

            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, fileInfo.toPath());
            } catch (Exception e) {
                log.debug("<br/> ERROR: " + e);
                writer.println("<br/> ERROR: " + e);
            }

        } catch (Exception e) {
            e.printStackTrace();
            writer.println("You either did not specify a file to upload or are " +
                    "trying to upload a file to a protected or nonexistent " +
                    "location.");
            writer.println("<br/> ERROR: " + e.getMessage());
            responseData.put("error", e.toString());

        } finally {
            responseData.put("link", linkName);
        }

        String jsonResponseData = new Gson().toJson(responseData);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponseData);

        return responseData;
    }

    private void nullCheckContentType(String contentTypeInfo, String contentType) throws Exception {
        if (contentTypeInfo == null || contentTypeInfo.toLowerCase().indexOf(contentType) == -1) {
            throw new Exception("Invalid contentType. It must be " + contentType);
        }
    }

    private String settingFullFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }
}
