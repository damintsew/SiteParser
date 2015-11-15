package com.damintsev.rest.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author adamintsev, <a href="mailto:Andrey.Damintsev@returnonintelligence.com">Andrey Damintsev</a>
 * @since 15 Нояб. 2015
 */
public class WallPostResponse implements ResponsePayload {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Long getPostId() {
        if (response != null)
            return response.getPostId();

        return null;
    }

    class Response {

        public Response() {
        }

        @JsonProperty("post_id")
        private Long postId;

        public Long getPostId() {
            return postId;
        }

        public void setPostId(Long postId) {
            this.postId = postId;
        }
    }
}
