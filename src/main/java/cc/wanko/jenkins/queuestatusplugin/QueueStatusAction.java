package cc.wanko.jenkins.queuestatusplugin;

import hudson.Extension;
import hudson.model.UnprotectedRootAction;

import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

@Extension
public class QueueStatusAction implements UnprotectedRootAction {
    @Override
    public String getUrlName() {
        return "queueStatus";
    }

    @Override
    public String getDisplayName() {
        return "Queue Status";
    }

    @Override
    public String getIconFileName() {
        return null;
    }

    public HttpResponse doIndex(StaplerRequest request, StaplerResponse response) {
        return new QueueStatusResponse();
    }
}

// vim: set sw=4 sts=4:
