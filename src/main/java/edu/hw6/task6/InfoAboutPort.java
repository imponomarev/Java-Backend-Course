package edu.hw6.task6;

import org.jetbrains.annotations.NotNull;

public record InfoAboutPort(int port, String protocol, String description) implements Comparable<InfoAboutPort> {

    @Override
    public int compareTo(@NotNull InfoAboutPort o) {
        return Integer.compare(port(), o.port());
    }
}
