FROM scratch
ADD ca-bundle.crt /etc/pki/tls/certs/ca-bundle.crt
ADD smart-gateway /
VOLUME /var/lib/sfproxy
CMD ["/smart-gateway", "-configfile", "/var/lib/sfproxy/etc/sfdbconfig.conf"] 


