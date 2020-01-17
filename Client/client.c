#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdlib.h>

int main(){
    struct hostent *host;
    struct sockaddr_in addr;
    char buf[256];

    host = 31.1.2.1
    int fd = socket(PF_INET, SOCK_STREAM, 0);
    addr.sin_family = AF_INET;
    addr.sin_port = htons(7777);
    memcpy(&addr.sin_addr.s_addr, host->h_addr, host->h_length);
    connect(fd, (struct sockaddr*)&addr, sizeof(addr));	

    strcpy(buf, "hello\0")
        
    write(fd, buf, 6);
    //odczyt info z serwera
//    int rc = read(fd, &buf, 256);
//    write(1, buf, rc);
    close(fd);
    return 0;
}