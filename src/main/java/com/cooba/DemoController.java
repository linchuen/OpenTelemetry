package com.cooba;


import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    private final Tracer tracer;

    public DemoController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping
    public String traceDemo() {
        Span span = tracer.currentSpan();
        if (span != null) {
            span.tag("custom.tag", "demo-call");
        }
        log.info("tracer log");
        return "Tracing to Jaeger!";
    }
}
