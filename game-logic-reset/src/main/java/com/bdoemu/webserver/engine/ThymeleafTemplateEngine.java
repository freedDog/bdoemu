package com.bdoemu.webserver.engine;

import com.bdoemu.core.configs.WebserverConfig;
import org.thymeleaf.context.AbstractContext;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.util.Map;

/**
 * @ClassName ThymeleafTemplateEngine
 * @Description TODO
 * @Author JiangBangMing
 * @Date 2019/7/11 15:36
 * VERSION 1.0
 */

public class ThymeleafTemplateEngine extends TemplateEngine {
    private static final String DEFAULT_SUFFIX = ".html";
    private static final String DEFAULT_TEMPLATE_MODE = "HTML";
    private static final long DEFAULT_CACHE_TTL_MS = 3600000L;
    private org.thymeleaf.TemplateEngine thymeleaf;

    public ThymeleafTemplateEngine() {
        final FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setTemplateMode(DEFAULT_TEMPLATE_MODE);
        templateResolver.setPrefix(WebserverConfig.DATA_PATH);
        templateResolver.setSuffix(DEFAULT_SUFFIX);
        templateResolver.setCacheTTLMs(DEFAULT_CACHE_TTL_MS);
        templateResolver.setCacheable(false);
        (this.thymeleaf = new org.thymeleaf.TemplateEngine()).setTemplateResolver( templateResolver);
    }

    @Override
    public String render(final ModelAndView modelAndView) {
        if (modelAndView.getModel() instanceof Map) {
            for (final Object key : ((Map) modelAndView.getModel()).keySet()) {
                if (!(key instanceof String)) {
                    throw new IllegalArgumentException("All keys of the model must be Strings");
                }
            }
            final Map<String, Object> modelMap = (Map<String, Object>) modelAndView.getModel();
            final AbstractContext ctx = (AbstractContext) new Context();
            ctx.setVariables((Map) modelMap);
            return this.thymeleaf.process(modelAndView.getViewName(), ctx);
        }
        throw new IllegalArgumentException("modelAndView must be of type java.util.Map");
    }
}
