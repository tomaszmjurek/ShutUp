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
	int local_port = 7878;
	
	// Bind to specific network and port
	struct sockaddr_in localaddr;
	localaddr.sin_family = AF_INET;
	localaddr.sin_addr.s_addr = INADDR_ANY;
	localaddr.sin_port = htons(local_port);

	// Connect to remote server
	struct sockaddr_in remoteaddr;
	remoteaddr.sin_family = AF_INET;
	remoteaddr.sin_addr.s_addr = inet_addr("192.168.0.11");
	remoteaddr.sin_port = htons(7777);

	// Wait for the server to be available
	int con = -1;
	printf("Waiting for server\n");
	while(con == -1) {
		con = connect(sockfd, (struct sockaddr *)&remoteaddr, sizeof(remoteaddr));
		sleep(1); // for testing less
		printf(". ");	
	}
	printf("\nConnected: %d\n", con);

    	// Read command from remote
	char buf[16]; // ex. 'shutdown -h now'
	printf("Waiting for message\n");
	int rcv = 1;
	rcv = read(sockfd, &buf, 16);

	if (rcv == -1) {
		printf("Error reading command\n");
		return -1;
	}

	//printf("%s\n", buf);

	// Close socket and execute command
	close(sockfd);	
	system(buf);
    	return 0;
}
