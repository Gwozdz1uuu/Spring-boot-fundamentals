package com.Gwozdz1uu.store.OldStaff;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class HeavyResource {
    public HeavyResource() {
        System.out.println("HeavyResource created");
    }
}
