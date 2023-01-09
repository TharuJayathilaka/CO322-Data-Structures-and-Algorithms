/*
	Author  : E/16/156 Jayathilaka H.A.D.T.T.	
	Date	: 22/10/2020	
	Usage 	:
		to compile  : gcc -o test lab4part1.c
		to run		: ./test wordlist1000.txt
		to stop run : enter any non-alphabetic character or Ctrl+c
*/


#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>
#include<string.h>
#include<time.h>
#include<ctype.h>

// Alphabet size (# of symbols)
#define ALPHABET_SIZE (26)

// count to get the memory size
int count=0;

typedef struct trieNode{
    struct trieNode *children[ALPHABET_SIZE];
    char letter;
    bool isEndOfWord;
}TrieNode;

// the functions which were built below
// use the function names given in the labsheet
TrieNode* createNode();
void insertWord();
TrieNode* searchWord();
void printSuggetions();
int getAsciiIndex(char letter);

int main(int argc, char**argv){
	
	// should have two arguments
    if (argc != 2 ){
        printf("usage: ./%s <<< file name   ->   example : wordlist1000.txt >>> \n",argv[0]);
        return -1;
    }else{
		int i=0,j=0;
		char text[100],str[100];
		// read the given file which was given as an argument
		FILE *readFile = fopen(argv[1],"r");
		//if file is empty print an erroe msg and terminate
		if (readFile == NULL){
			printf("The file is empty");
			return -1;
		}
		// create a trie node
		TrieNode* head = createNode();
		// count the time taken add information to the tree
		// get thr start time
		clock_t start = clock();
		
		while(!feof(readFile)){
			fscanf(readFile,"%s",str);
			// get word one by one 
			while(str[j]){
				//convert them to lowercase and store, since we are asked to get the results in lowercase
				if(isupper(str[j])) str[j] = tolower(str[j]);
				j++;
			}
			//printf("%s",str);
			insertWord(head,str);
		}
		// get the end time
		clock_t end = clock();
		// close the file reading
		fclose(readFile);
		// calculate the time taken
		double timeTaken = (double)(end-start)/CLOCKS_PER_SEC;
		
		// print the calculations
		printf("\nElapsed time to store: %.8lf s\n",timeTaken);
		printf("\nMemory: %lu Bytes\n\n",sizeof(*(head))*count);

		while(1){
			printf("Enter the non-compelte word: ");
			scanf("%s",&text);
			// get the start time
			clock_t start = clock();
			// search the keyword store the result in a new node called noode
			TrieNode* Node = searchWord(head,text);
			if(Node != NULL){
				printf("\nThe matching suggestions to %s\n\n",text);
				int len=strlen(text);
				// call the function printSuggetions to print the results
				printSuggetions(Node,text,len);
				// get the end time
				clock_t end = clock();
				//calculate the time taken for the task
				double elapsed_time = (double)(end-start)/CLOCKS_PER_SEC;
				//print the calculated time
				printf("\nElapsed time: %f s\n\n",elapsed_time);
			}else{
				// get the end time if it doesnot match
				clock_t end = clock();
				// if the search keyword empty that means that word is not stored
				printf("There aren't any matching suggestions\n\n");
				//calculate the time taken for the task
				double elapsed_time = (double)(end-start)/CLOCKS_PER_SEC;
				//print the calculated time
				printf("\nElapsed time: %f s\n\n",elapsed_time);
			}
		}
		return 0;
	}
}

int getAsciiIndex(char letter){ 
	// this is not required since we store each word in lowercase
    if((int)letter >= 65 && (int)letter <= 90){
		return tolower(letter)-'a';  
    }else if( (int)letter >= 97 && (int)letter <= 122){
		// get the relevant ascii value of characters
		return letter-'a';
    }else{
		return -1;
	}
}

TrieNode* createNode(){
	//trieNode is created so that it the size can be adjustable for the words in the text
    TrieNode* newNode = (TrieNode*)malloc(sizeof(TrieNode));
	int k;
	// initialize the variables in the node
    for (k = 0; k<26; k++){
    	newNode->children[k] = NULL;
    }
	// initialize the variables in the node 
    newNode->letter='0';
    newNode->isEndOfWord = false;
	// return the created node
    return newNode;
}

void insertWord(TrieNode *head,char *str){
    int i,indexNo;
	// get the length od the word which want to be inserted
	int length = strlen(str);
	// start from the head node
    TrieNode* temp = head;
    for(i=0;i<length;i++){
		// get the ascii index of eah character in that word
        indexNo = getAsciiIndex(str[i]);
        if(indexNo >= 0){
			// create a new node if path doesn't exit
			if(!temp->children[indexNo]){
				temp->children[indexNo] = createNode();
				// store the relevant letter
				temp->children[indexNo]->letter = str[i];
			}
			// go to the next node
            temp = temp->children[indexNo];
        }
    }
	// mark the current node as the end
    temp->isEndOfWord = true;
    count++;
}

TrieNode* searchWord(TrieNode* head,char* str){
	int i,indexNo;
	int length = strlen(str);
	// return 0 if trie is empty
	if(head==NULL){
		printf("\nThe trie is empty.\n");
		return 0;
	}
	// start from the head node
    TrieNode* currtemp = head;
    for(i=0;i<length;i++){
		// get the ascill index of each character of the word
        indexNo = getAsciiIndex(str[i]);
		// if the string is valid
        if(currtemp != NULL)
			currtemp =currtemp->children[indexNo];
    }
	// return the search word.
    return currtemp;
}

void printSuggetions(TrieNode* Node,char str[],int strSize){
	int i;
    // if the end of the word has arrived
    if(Node->isEndOfWord){
		// print all the characters of the word
        for(i=0;i<strSize;i++){
            printf("%c",str[i]);
        }
        printf("\n");
    }
    for(i=0;i<26;i++){
        if(Node->children[i]!=NULL){
			// get the relevant character to the str array 
            str[strSize] = Node->children[i]->letter;
			// recursive function
            printSuggetions(Node->children[i],str,strSize+1);
        }
    }
}

