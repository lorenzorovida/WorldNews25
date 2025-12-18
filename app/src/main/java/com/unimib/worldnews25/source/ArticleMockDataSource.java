package com.unimib.worldnews25.source;

import static com.unimib.worldnews25.utils.Constants.API_KEY_ERROR;

import com.unimib.worldnews25.model.ArticleAPIResponse;
import com.unimib.worldnews25.utils.JSONParserUtils;
import com.unimib.worldnews25.utils.Constants;

import java.io.IOException;

public class ArticleMockDataSource extends BaseArticleRemoteDataSource {

    private final JSONParserUtils jsonParserUtil;

    public ArticleMockDataSource(JSONParserUtils jsonParserUtil) {
        this.jsonParserUtil = jsonParserUtil;
    }

    @Override
    public void getArticles(String country) {
        ArticleAPIResponse articleAPIResponse = null;

        try {
            articleAPIResponse = jsonParserUtil.parseJSONFileWithGSon(Constants.SAMPLE_JSON_API_RESPONSE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (articleAPIResponse != null) {
            articleCallback.onSuccessFromRemote(articleAPIResponse, System.currentTimeMillis());
        } else {
            articleCallback.onFailureFromRemote(new Exception(API_KEY_ERROR));
        }
    }
}
