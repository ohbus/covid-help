#
# The MIT License
# Copyright © 2021 Subhrodip Mohanta
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
#

FROM openjdk:11-jre-slim

LABEL maintainer="Subhrodip Mohanta hello@subho.xyz"
LABEL org.opencontainers.image.source="https://github.com/ohbus/covid-help"
LABEL group="xyz.subho"
LABEL artifact="covid-help"
LABEL name="COVID Help"

ENV MYSQL_DB_HOST=10.10.1.36
ENV MYSQL_DB_PORT=3310
ENV MYSQL_DB_UNAME=subho
ENV MYSQL_DB_PASSWD=subho

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "-Dspring.profiles.active=dev", "/app.jar" ]
