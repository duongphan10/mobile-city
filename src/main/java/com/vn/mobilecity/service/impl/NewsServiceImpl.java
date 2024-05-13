package com.vn.mobilecity.service.impl;

import com.vn.mobilecity.constant.ErrorMessage;
import com.vn.mobilecity.constant.MessageConstant;
import com.vn.mobilecity.domain.dto.request.NewsRequestDto;
import com.vn.mobilecity.domain.dto.response.CommonResponseDto;
import com.vn.mobilecity.domain.dto.response.NewsDto;
import com.vn.mobilecity.domain.entity.News;
import com.vn.mobilecity.domain.entity.NewsType;
import com.vn.mobilecity.domain.mapper.NewsMapper;
import com.vn.mobilecity.exception.NotFoundException;
import com.vn.mobilecity.repository.NewsRepository;
import com.vn.mobilecity.repository.NewsTypeRepository;
import com.vn.mobilecity.service.NewsService;
import com.vn.mobilecity.util.UploadFileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsTypeRepository newsTypeRepository;
    private final UploadFileUtil uploadFileUtil;
    private final NewsMapper newsMapper;

    @Override
    public NewsDto getById(Integer id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.News.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        return newsMapper.mapNewsToNewsDto(news);
    }

    @Override
    public List<NewsDto> getByUser() {
        List<News> newsList = newsRepository.getByUser();
        return newsMapper.mapNewsToNewsDto(newsList);
    }

    @Override
    public List<NewsDto> getAll(Boolean status) {
        List<News> newsList = newsRepository.getAll(status);
        return newsMapper.mapNewsToNewsDto(newsList);
    }

    @Override
    public NewsDto create(NewsRequestDto createDto) {
        NewsType newsType = newsTypeRepository.findById(createDto.getNewsTypeId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NewsType.ERR_NOT_FOUND_ID,
                        new String[]{createDto.getNewsTypeId().toString()}));

        News news = newsMapper.mapNewsCreateDtoToNews(createDto);
        news.setAvatar(uploadFileUtil.uploadImage(createDto.getAvatar()));
        news.setNewsType(newsType);
        return newsMapper.mapNewsToNewsDto(newsRepository.save(news));
    }

    @Override
    public NewsDto update(Integer id, NewsRequestDto updateDto) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.News.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        NewsType newsType = newsTypeRepository.findById(updateDto.getNewsTypeId())
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NewsType.ERR_NOT_FOUND_ID,
                        new String[]{updateDto.getNewsTypeId().toString()}));
        newsMapper.updateNews(news, updateDto);

        if (updateDto.getAvatar() != null) {
            uploadFileUtil.destroyImageWithUrl(news.getAvatar());
            news.setAvatar(uploadFileUtil.uploadImage(updateDto.getAvatar()));
        }
        news.setNewsType(newsType);
        return newsMapper.mapNewsToNewsDto(newsRepository.save(news));
    }

    @Override
    public CommonResponseDto deleteById(Integer id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.News.ERR_NOT_FOUND_ID, new String[]{id.toString()}));
        uploadFileUtil.destroyImageWithUrl(news.getAvatar());
        newsRepository.delete(news);
        return new CommonResponseDto(true, MessageConstant.DELETE_NEWS_SUCCESSFULLY);
    }
}
