FROM mono:3.10

# Create app directory
RUN mkdir -p /usr/src/livrair_ext
WORKDIR /usr/src/livrair_ext

COPY server.exe .

# Copy the startup script
RUN ["chmod", "u+x", "server.exe"]

EXPOSE 9090

ENTRYPOINT [ "mono",  "./server.exe" ]
CMD ["/standalone"]
