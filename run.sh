#!/bin/sh

cd project

gradle build

cd ..

cp "project/build/libs/snake.jar" "."

java -jar SnakeVisualiser2014.jar -j snake.jar -speed 50
