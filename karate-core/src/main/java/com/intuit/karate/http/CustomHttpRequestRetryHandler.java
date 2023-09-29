package com.intuit.karate.http;

import java.io.IOException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.protocol.HttpContext;

import com.intuit.karate.Logger;

public class CustomHttpRequestRetryHandler implements HttpRequestRetryHandler
{
    private final Logger logger;

    public CustomHttpRequestRetryHandler(Logger logger)
    {
        this.logger = logger;
    }

    @Override
    public boolean retryRequest(IOException exception, int executionCount, HttpContext context)
    {
        if (exception instanceof NoHttpResponseException && executionCount < 1)
        {
            logger.error("Thrown an NoHttpResponseException retry...");
            return true;
        }
        else
        {
            logger.error("Thrown an exception {}", exception.getMessage());
            return false;
        }
    }
}
