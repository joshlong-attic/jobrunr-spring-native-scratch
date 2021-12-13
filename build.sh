#!/usr/bin/env bash
./mvnw -DskipTests=true -Pnative clean package && ./target/jobrunr-spring-native-scratch
