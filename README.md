# File-Retrieval-Engine-Remote-Procedure-Call-

### Requirements

To run the C++ programs you will need to have GCC 12.x and CMake 3.22.x installed on your system. You will also need to install Google Protocol Buffers (ProtoBuf) and gRPC libraries and development files. On Ubuntu 22.04 you can install GCC, CMake, ProtoBuf and gRPC, and set GCC as default compiler using the following commands:


sudo apt install g++-12 gcc-12 cmake
sudo update-alternatives --remove-all gcc
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-11 110
sudo update-alternatives --install /usr/bin/gcc gcc /usr/bin/gcc-12 120
sudo update-alternatives --remove-all g++
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-11 110
sudo update-alternatives --install /usr/bin/g++ g++ /usr/bin/g++-12 120
sudo apt install protobuf-compiler libprotobuf-dev protobuf-compiler-grpc libgrpc++-dev


To run the Java programs you will need to have Java 1.7.x and Maven 3.6.x installed on your systems. On Ubuntu 22.04 you can install Java and Maven using the following commands:


sudo apt install openjdk-17-jdk maven



### Setup

There are 5 datasets (Dataset1, Dataset2, Dataset3, Dataset4, Dataset5) that you need to use to evaluate the indexing performance of your solution.
Before you can evaluate your solution you need to download the datasets. You can download the datasets from the following link:

https://depauledu-my.sharepoint.com/:f:/g/personal/aorhean_depaul_edu/EgmxmSiWjpVMi8r6QHovyYIB-XWjqOmQwuINCd9N_Ppnug?e=TLBF4V

After you finished downloading the datasets copy them to the dataset directory (create the directory if it does not exist).
Here is an example on how you can copy Dataset1 to the remote machine and how to unzip the dataset:


remote-computer$ mkdir datasets
local-computer$ scp Dataset1.zip cc@<remote-ip>:<path-to-repo>/datasets/.
remote-computer$ cd <path-to-repo>/datasets
remote-computer$ unzip Dataset1.zip



### Java solution
#### How to build/compile

To build the Java solution use the following commands:

cd app-java

mvn compile

mvn package



#### How to run application

To run the Java server (after you build the project) use the following command:


java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.FileRetrievalServer <port>
> <quit>




To run the Java client (after you build the project) use the following command:


java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.FileRetrievalClient
> <connect | index | search | quit>





#### Example (2 clients and 1 server)

**Step 1:** start the server:

Server

java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.FileRetrievalServer
>



**Step 2:** start the clients and connect them to the server:

Client 1

java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.FileRetrievalClient
> connect 127.0.0.1 12345

Connection successful!


Client 2

java -cp target/app-java-1.0-SNAPSHOT.jar csc435.app.FileRetrievalClient
> connect 127.0.0.1 12345

Connection successful!




**Step 3:** index files from the clients:

Client 1


> index /home/kinjal/datasets/Dataset1/folder1
Received command: 'index /home/kinjal/datasets/Dataset1/folder1'
Indexing time: 1.758178753 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder3
Received command: 'index /home/kinjal/datasets/Dataset1/folder3'
Indexing time: 2.797229306 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder5
Received command: 'index /home/kinjal/datasets/Dataset1/folder5'
Indexing time: 0.808602617 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder7
Received command: 'index /home/kinjal/datasets/Dataset1/folder7'
Indexing time: 1.07323623 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder9
Received command: 'index /home/kinjal/datasets/Dataset1/folder9'
Indexing time: 1.577109262 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder11
Received command: 'index /home/kinjal/datasets/Dataset1/folder11'
Indexing time: 1.285239879 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder13
Received command: 'index /home/kinjal/datasets/Dataset1/folder13'
Indexing time: 2.279397651 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder15
Received command: 'index /home/kinjal/datasets/Dataset1/folder15'
Indexing time: 2.140188249 seconds

Indexing completed!


Client 2


> index /home/kinjal/datasets/Dataset1/folder2
Received command: 'index /home/kinjal/datasets/Dataset1/folder2'
Indexing time: 3.92138929 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder4
Received command: 'index /home/kinjal/datasets/Dataset1/folder4'
Indexing time: 1.006372829 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder6
Received command: 'index /home/kinjal/datasets/Dataset1/folder6'
Indexing time: 1.860110591 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder8
Received command: 'index /home/kinjal/datasets/Dataset1/folder8'
Indexing time: 1.457241671 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder10
Received command: 'index /home/kinjal/datasets/Dataset1/folder10'
Indexing time: 1.765470675 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder12
Received command: 'index /home/kinjal/datasets/Dataset1/folder12'
Indexing time: 1.007370521 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder14
Received command: 'index /home/kinjal/datasets/Dataset1/folder14'
Indexing time: 2.952704393 seconds
Indexing completed!
> index /home/kinjal/datasets/Dataset1/folder16
Received command: 'index /home/kinjal/datasets/Dataset1/folder16'
Indexing time: 1.889107136 seconds

Indexing completed!




**Step 4:** search files from the clients:

Client 1


> search Worms
Received command: 'search Worms'
Search completed in 0.432805334 seconds
Search results (top 10):
* /home/kinjal/datasets/Dataset1/folder6/document200.txt 10
* /home/kinjal/datasets/Dataset1/folder6/document424.txt 4
* /home/kinjal/datasets/Dataset1/folder14/document417.txt 4
* /home/kinjal/datasets/Dataset2/folder2/document894.txt 2
* /home/kinjal/datasets/Dataset1/folder13/document272.txt 1
* /home/kinjal/datasets/Dataset2/folder2/document101.txt 1
* /home/kinjal/datasets/Dataset1/folder11/document79.txt 1
* /home/kinjal/datasets/Dataset1/folder15/document351.txt 1
* /home/kinjal/datasets/Dataset1/folder4/document101.txt 1
* /home/kinjal/datasets/Dataset1/folder1/document260.txt 1
  
Search completed!
  

Client 2


> search distortion AND adaptation
Received command: 'search distortion AND adaptation'
Search completed in 0.019427095 seconds
Search results (top 10):
* /home/kinjal/datasets/Dataset1/folder6/document200.txt 49
* /home/kinjal/datasets/Dataset1/folder5/document474.txt 15
* /home/kinjal/datasets/Dataset1/folder8/document22.txt 6
* /home/kinjal/datasets/Dataset1/folder8/document477.txt 5
* /home/kinjal/datasets/Dataset2/folder2/document686.txt 5
* /home/kinjal/datasets/Dataset2/folder2/document874.txt 5
* /home/kinjal/datasets/Dataset1/folder13/document38.txt 4
* /home/kinjal/datasets/Dataset1/folder6/document408.txt 3
* /home/kinjal/datasets/Dataset1/folder8/document252.txt 3
* /home/kinjal/datasets/Dataset1/folder2/document404.txt 3
  
Search completed!

> quit




**Step 5:** close and disconnect the clients:

Client 1

> quit


Client 2

> quit




**Step 6:** close the server:

Server

> quit

