# queue-status-plugin
Add simple JSON API to retrieve build queue status.

```
% curl -s http://localhost:8080/jenkins/queueStatus/ | jq .
{
  "blocked": {},
  "buildable": {
    "@null": 1,
    "ruby": 1
  }
}
```

`@null` means the queued item has no assigned label.
