package com.light.saber.webclient


import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage
import org.springframework.stereotype.Service

@Service
class CrawlerWebClient {

    private fun instanceWebClient(javaScriptTimeout: Long): WebClient {
        val webClient = WebClient()
        if (javaScriptTimeout > 0) {
            webClient.javaScriptTimeout = javaScriptTimeout
        }
        webClient.options.isJavaScriptEnabled = true //启用JS解释器，默认为true
        webClient.options.isCssEnabled = false
        webClient.options.isThrowExceptionOnScriptError = false //js运行错误时，是否抛出异常
        webClient.options.isUseInsecureSSL = true
        return webClient as WebClient
    }

    fun getPageHtmlText(url: String): String? {
        val webClient = instanceWebClient(3000)
        return try {
            webClient.getPage<HtmlPage>(url).asXml()
        } catch (e: Exception) {
            null
        }
    }

}
