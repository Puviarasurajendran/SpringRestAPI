package com.zoho.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
     System.out.println("<><><><><><><><><><><><><><><><><><><>Inside Spring Project getServletConfigClasses");
          return new Class[] { AppConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/" };
    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        super.onStartup(servletContext);
//
//        System.out.println("Inside Spring onStartup method");
//
//        // Determine which profile to activate based on runtime logic
//        String activeProfile = determineActiveProfile(servletContext);
//
//        // Set active profile dynamically
//        servletContext.setInitParameter("spring.profiles.active", activeProfile);
//    }
//
//    private String determineActiveProfile(ServletContext servletContext) {
//        // Example logic to determine which profile to activate based on path versioning
//        // Replace with your actual logic to determine "v1" or "v2"
//        String contextPath = servletContext.getContextPath();
//        String versionFromPath = contextPath.contains("/v2/") ? "v2" : "v1"; // Example: Fetch version from context path or configuration
//
//        return versionFromPath;
//    }


}
