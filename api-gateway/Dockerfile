FROM kong:latest
USER root
RUN luarocks config --lua-ver
RUN luarocks install kong-jwt2header
USER kong
