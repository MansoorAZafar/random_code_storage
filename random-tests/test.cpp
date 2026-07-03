#include <iostream>
#include <string.h>
#include <stdio.h>
#include <sys/ioctl.h>
#include <net/if.h>
#include <net/if_arp.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <string>
#include <unordered_map>

#define NAME_SIZE 16

using namespace std;

template <class Value, class Request>
void printNetworkInformation(int fd, Request req, ifreq*& ifr, Value* value, const char* message) {
    int ret = ioctl(fd, req, &ifr);
    if (ret < 0) {
        std::cout << strerror(errno);
        return;
    }

    const sockaddr_in converted = static_cast<sockaddr_in>(&(*value));
    const char* value = converted->sin_addr;
    
    std::cout << message << value << "\n";
    // if(req == SIOCGIFADDR) {

    // } else if (req == SIOCGIFNETMASK) {

    // } else {

    // }
}

int main()
{
    std::unordered_map<
    int fd;
    int ret;
    int selection;
    struct ifreq ifr;
    char if_name[NAME_SIZE];
    unsigned char *mac=NULL;

    cout << "Enter the interface name: ";
    cin >> if_name;

    size_t if_name_len=strlen(if_name);
    if (if_name_len<sizeof(ifr.ifr_name)) {
        memcpy(ifr.ifr_name,if_name,if_name_len);
        ifr.ifr_name[if_name_len]=0;
    } else {
        cout << "Interface name is too long!" << endl;
	return -1;
    }

    fd = socket(AF_INET, SOCK_DGRAM, 0);
    if(fd<0) {
        cout << strerror(errno);
	return -1;
    }

    system("clear");
    do {
        cout << "Choose from the following:" << endl;
	cout << "1. Hardware address" << endl;
	cout << "2. IP address" << endl;
	cout << "3. Network mask" << endl;
	cout << "4. Broadcast address" << endl;
	cout << "0. Exit" << endl << endl;
	cin >> selection;
	switch(selection) {
        case 1:
            ret = ioctl(fd, SIOCGIFHWADDR, &ifr);
            if(ret<0) {
                cout << strerror(errno);
            } else if(ifr.ifr_hwaddr.sa_family!=ARPHRD_ETHER) {
                cout << "not an Ethernet interface" << endl;
            } else {
                mac=(unsigned char*)ifr.ifr_hwaddr.sa_data;
                printf("MAC Address: %02X:%02X:%02X:%02X:%02X:%02X\n", mac[0],mac[1],mac[2],mac[3],mac[4],mac[5]);
	    }
            break;
        case 2:
	    // ret = ioctl(fd, SIOCGIFADDR, &ifr);
	    // if(ret < 0) {
		// perror("ioctl");
	    // } else {
		// std::cout << "IP Address: " << inet_ntoa((((struct sockaddr_in*)&ifr.ifr_addr)->sin_addr)) << "\n";	
	    // }
        printNetworkInformation(fd, SIOCGIFADDR, ifr, )
	    break;
        case 3:
            ret = ioctl(fd, SIOCGIFNETMASK, &ifr);
            if(ret < 0) {
		std::cout << strerror(errno);
	    } else {
		std::cout << "Network mask: " << inet_ntoa(((struct sockaddr_in*)&ifr.ifr_netmask)->sin_addr) << "\n";
	    }
	    break;
        case 4:
	    ret = ioctl(fd, SIOCGIFBRDADDR, &ifr);
	    if(ret < 0) {
		std::cout << strerror(errno);
	    } else {
		std::cout << "Broadcast Address: " << inet_ntoa(((struct sockaddr_in*)&ifr.ifr_broadaddr)->sin_addr) << "\n";
	    }
            break;
        }
	if(selection!=0) {
            char key;
            cout << "Press any key to continue: ";
            cin >> key;
            system("clear");
        }
    } while (selection!=0);


    close(fd); 
    return 0;
}

