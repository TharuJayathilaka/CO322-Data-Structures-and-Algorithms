/*
	Author  : E/16/156 Jayathilaka H.A.D.T.T.	
	Date	: 23/10/2020	
	Usage 	:
		to compile  : gcc -o test lab4part2.c
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

typedef struct linkedStr{
	char letter;
	struct linkedStr *next;
}LinkedStr;

typedef struct trieNode{
    struct trieNode* children[ALPHABET_SIZE];
    LinkedStr* LinkedStr;
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
			printf("The file cannot be opened");
			return -1;
		}
		// create a trie node(head or root)
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
	newNode->LinkedStr = NULL;
    newNode->isEndOfWord = false;
	// return the created node
    return newNode;
}

void insertWord(TrieNode* head,char* str){
   
	// define the variables which are going to use and initialize them
    TrieNode *currtemp = head;
	TrieNode *prevtemp = NULL;
    TrieNode *newtemp1 = NULL;
	TrieNode *newtemp2 = NULL;
	TrieNode *newtemp3 = NULL;
    LinkedStr *currStr;
	LinkedStr *prevStr = NULL;
    currStr = currtemp->LinkedStr;
	int length = strlen(str);
    int i=0,indexNo;
	
    while(i<=length){
		// get the ascii value index relavant to the character i in the given word
    	indexNo = getAsciiIndex(str[i]);
		// index number should be positive
        if(indexNo >= 0){
			
			// if the current linked string is empty
    	    if(currStr == NULL){
				// if the current node the relavant character is empty, create a new node and store the current string in a linked list
				if(currtemp->children[indexNo] == NULL){
					//create a new node and a new linkedstring for that character.
					newtemp1 = createNode();
					LinkedStr* current1 = (LinkedStr*) malloc(sizeof(LinkedStr));
					// enter the character to the linked list and the set the next address as null
					current1 -> letter = str[i];
					current1 -> next = NULL;
					// get two new linked list to store the data which we have now to use it later.
					LinkedStr* newStr1 = NULL;
					LinkedStr* string1 = current1;
					
					// store the next characters of the word in a linked list
					int k;
					for(k=i+1;k<length;k++){
						newStr1 = (LinkedStr*) malloc(sizeof(LinkedStr));
						// store the character
						newStr1 -> letter = str[k];
						// set the path
						newStr1 -> next = NULL;
						current1->next = newStr1;
						current1 = current1->next;
					}
					//current1 = NULL;
					// add the stored values to current linked string under the relavent character
					newtemp1->LinkedStr = string1;
                    newtemp1->isEndOfWord = true;
					currtemp->children[indexNo] = newtemp1;
					count++;
					break;
				}else{
					// if the current node the relavant character is not empty, go to the next character linked list 
                    prevtemp = currtemp;
                    currtemp = currtemp->children[indexNo];
                    prevStr = currtemp->LinkedStr;
					currStr = currtemp->LinkedStr->next;
				}
    	    }else{
				// if the current linked string is not empty
				
				// and the linked list doesnot have the relavant character
				if (currStr->letter != str[i]){
					
					//create a new node for that character. 
					// initialize the values of the new node and make it as the current node
					// set the values of other nodes according to that
					newtemp2 = createNode();
					newtemp2->LinkedStr = currtemp->LinkedStr;
					LinkedStr* newStr = prevStr->next;
					prevStr->next = NULL;
					currtemp->LinkedStr = newStr;
					int index1 = getAsciiIndex(newStr->letter);
					newtemp2->children[index1] = currtemp;
					int index2 = getAsciiIndex(newtemp2->LinkedStr->letter);
					prevtemp->children[index2] = newtemp2;
					currtemp = newtemp2;
					
					//create a new node and a new linkedstring for that character.
					newtemp3 = createNode();
					LinkedStr* current2 = (LinkedStr*) malloc(sizeof(LinkedStr));
					// enter the character to the linked list and the set the next address as null
					current2 -> letter = str[i];
					current2 -> next = NULL;
					// get two new linked list to store the data which we have now to use it later.
					LinkedStr* newStr2 = NULL;
					LinkedStr* string2 = current2;
					
					// store the next characters of the word in a linked list
					int p;
					for(p=i+1;p<length;p++){
						newStr2 = (LinkedStr*) malloc(sizeof(LinkedStr));
						// store the character
						newStr2 -> letter = str[p];
						// set the path
						newStr2 -> next = NULL;
						current2->next = newStr2;
						current2 = current2->next;
					}
					//current2 = NULL;
					// add the stored values to current linked string under the relavent character
					newtemp3->LinkedStr = string2;
                    newtemp3->isEndOfWord = true;
					currtemp->children[indexNo] = newtemp3;
					break;
				}else{
					// if the current linked string is not empty and the linked list has the relavant character, set the path to the next character
                    prevStr = currStr;
					currStr = currStr->next;
				}
    	    }
        }
		// go to the next character of the word
    	i++;
    }
}

TrieNode* searchWord(TrieNode* head,char* str){
    
	// define the variables which are going to use and initialize them
    TrieNode* currtemp = head;
    LinkedStr* currLetter = currtemp->LinkedStr;
	int length = strlen(str);
    int i=0,indexNo,validity=1;
	
	// return 0 if trie is empty
	if(head==NULL){
		printf("\nThe trie is empty.\n");
		return 0;
	}
    
	// compare the words character by character
    while(i<length){
		// get the ascii value index relavant to the character i in the given word
    	indexNo = getAsciiIndex(str[i]);
		
		//  
    	if(currLetter == NULL && validity){
            currtemp = currtemp->children[indexNo];
			// if the string is valid, go to next
            if(currtemp != NULL){
				currLetter = currtemp->LinkedStr->next;
            }else{
				validity=0;
            }
    	}else if(currLetter != NULL && validity){    
    	    currLetter = currLetter->next;
    	}
        i++;
    }
	// make requirements that need to find the full word.
	// increse the size of the non complete word and make the next letter as the current letter
	while(currLetter != NULL){
    	str[length] = currLetter->letter;
    	currLetter = currLetter->next;
    	length++;
    }
    str[length] = '\0';

    return currtemp;
}

void printSuggetions(TrieNode* Node,char str[],int strSize){
    int i,j,size; 
	
	// if the end of the word has arrived
    if(Node->isEndOfWord){
        for (j=0;j<strSize;j++){
			// print all the characters of the word
            printf("%c",str[j]);
        }
        printf("\n");
    }

    for (i=0;i<26;i++){
        if(Node->children[i]!=NULL){
		    LinkedStr* currLetter = Node->children[i]->LinkedStr;
			size=strSize;
			// print all the words ralated to that non complete word 
		    while (currLetter != NULL){
				str[size] = currLetter->letter;
				currLetter = currLetter->next;
				size++;
			}
			str[size] = '\0';
			// recursive function to print all the suggetions
			printSuggetions(Node->children[i],str,size); 
        }
    }
}

