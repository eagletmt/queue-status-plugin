package cc.wanko.jenkins.queuestatusplugin;

import jenkins.model.Jenkins;

import hudson.model.Queue;
import hudson.model.Label;

import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

import javax.servlet.ServletException;

import java.io.IOException;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

class QueueStatusResponse implements HttpResponse {
    @Override
    public void generateResponse(StaplerRequest request, StaplerResponse response, Object node) throws IOException, ServletException {
        JSONObject object = collectQueueStatus();

        response.setHeader("Content-Type", "application/json");
        object.write(response.getWriter());
    }

    private JSONObject collectQueueStatus() {
        Jenkins jenkins = Jenkins.getInstance();
        Map<String, Integer> blocked = new HashMap<String, Integer>();
        Map<String, Integer> buildable = new HashMap<String, Integer>();

        for (Queue.Item item : jenkins.getQueue().getItems()) {
            Label label = item.getAssignedLabel();
            String key = label == null ? "@null" : label.toString();
            Map<String, Integer> m = item.isBlocked() ? blocked : buildable;
            if (m.containsKey(key)) {
                m.put(key, m.get(key) + 1);
            } else {
                m.put(key, 1);
            }
        }

        JSONObject object = new JSONObject();
        object.put("blocked", blocked);
        object.put("buildable", buildable);

        return object;
    }
}

// vim: set sw=4 sts=4:
