#!/bin/sh

mv "project/build/classes/artifacts/snake_jar/snake.jar" "."

java -jar SnakeVisualiser2014.jar -j snake.jar -speed 50
