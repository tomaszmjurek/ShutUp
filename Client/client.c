#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <string.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <netdb.h>
#include <stdlib.h>
#include <stdio.h>

int main(){
	int sockfd = socket(AF_INET, SOCK_STREAM, 0);
	
	// Bind to specific network and port
	struct sockaddr_in localaddr;
	localaddr.sin_family = AF_INET;
	localaddr.sin_addr.s_addr = INADDR_ANY; //inet_addr("31.1.2.1") //or yourself
	localaddr.sin_port = htons(1234); //inny?
	bind(sockfd, (struct sockaddr *)&localaddr, sizeof(localaddr));

	// Connect to remote server
	struct sockaddr_in remoteaddr;
	remoteaddr.sin_family = AF_INET;
	remoteaddr.sin_addr.s_addr = inet_addr("198.143.55.17"); //31.1.2.1
	remoteaddr.sin_port = htons(7777);

	//listen(sockfd, 10);
	int con = -1;
	while(con == -1) {
		con = connect(sockfd, (struct sockaddr *)&remoteaddr, sizeof(remoteaddr));
		sleep(5);
		printf(".\n");	
	}
	printf("Connected: %d\n", con);

	char buf[256];
 	strcpy(buf, "hello\0");
        
    	int sent = write(sockfd, buf, 6);
	printf("SENT: %d\n", sent);

    	// odczyt info z serwera
//    int rc = read(fd, &buf, 256);
//    write(1, buf, rc);
   	close(sockfd);
    	return 0;
}
