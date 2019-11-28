package trie;



import java.util.ArrayList;



/**

 * This class implements a Trie. 

 * 

 * @author prff/ Sesh Venugopal

 * 

 * 

 *@solved by Ahmed Aboukoura :) 

 */

public class Trie {

    

    // prevent instantiation

    private Trie() { }

    

    /**

     * Builds a trie by inserting all words in the input array, one at a time,

     * in sequence FROM FIRST TO LAST. (The sequence is IMPORTANT!)

     * The words in the input array are all lower case.

     * 

     * @param allWords Input array of words (lowercase) to be inserted.

     * @return Root of trie with all words inserted from the input array

     */

    public static TrieNode buildTrie(String[] allWords) {

        

              // if list is empty return the root

              TrieNode root = new TrieNode(null,null,null);

              if(allWords.length == 0) {

                  return root;

              }

              //creating first child

              Indexes tempp = new Indexes(0, (short) 0,(short)0);

              root.firstChild = new TrieNode(tempp, null, null);

              root.firstChild.substr.startIndex =  (short)(0);

              root.firstChild.substr.endIndex =  (short)(allWords[0].length() - 1);

              root.firstChild.substr.wordIndex = 0;

              TrieNode ptr = root.firstChild;

              ////////

              for(int i =1; i< allWords.length; i++) {

                  

                  String newWord = allWords[i];

                  

                  while(ptr!= null){

                      

                       // getting ptr word;

                      

                      String ptrWordtemp = allWords[ptr.substr.wordIndex];

                     String ptrWord = ptrWordtemp.substring(ptr.substr.startIndex, ptr.substr.endIndex +1);

                      // how much of the new word match the ptr word. 

                      int matchTill = 0; 

                      for(int j = 0; j < newWord.length(); j++ ){

                          if(matchTill < ptrWord.length() ) {

                              if(ptrWord.charAt(j)==newWord.charAt(j)) {

                                  matchTill++;

                                  continue;

                              }else {

                                  break;

                              }

                          }

                      }

                      // if there is no match go to the sibling;

                      if(matchTill == 0) {

                          if(ptr.sibling == null) {

                              Indexes temp = new Indexes(i,ptr.substr.startIndex,(short)(allWords[i].length()-1));

                              ptr.sibling = new TrieNode(temp,null,null);

                              break;

                          }

                          ptr = ptr.sibling;

                          continue;

                      }

                      // if there is full match got to the ptr first child

                      if(matchTill == ptrWord.length()) {

                          ptr = ptr.firstChild;

                          String stemp =  newWord.substring(matchTill, newWord.length());

                          newWord = stemp;



                          continue;

                      }

                      // if there is a partial match split. 

                      if(matchTill != ptrWord.length() && matchTill > 0 ) {



                          TrieNode tempchild = new TrieNode(null,null,null);

                          tempchild = ptr.firstChild;

                          Indexes temp = new Indexes(0,(short)0,(short)0);

                          TrieNode secondsibling = new TrieNode(temp,null,null);

                          secondsibling.substr.wordIndex = i;

                          secondsibling.substr.startIndex = (short)(matchTill + ptr.substr.startIndex); 

                          secondsibling.substr.endIndex = (short) (allWords[i].length()-1); 

                          ///

                          Indexes temp2 = new Indexes(0,(short)0,(short)0);

                          TrieNode FirstChild = new TrieNode(temp2,tempchild,null);

                          FirstChild.substr.wordIndex = ptr.substr.wordIndex;

                          FirstChild.substr.startIndex = (short)(matchTill + ptr.substr.startIndex); 

                          FirstChild.substr.endIndex = (short)ptr.substr.endIndex;

                          FirstChild.sibling = secondsibling;

                          ptr.firstChild = FirstChild;

                          

                          ptr.substr.endIndex = (short) (matchTill -1 + ptr.substr.startIndex) ;

                          break;

                      }

                  }

                   ptr = root.firstChild;

                  

             }

        return root;

    }

    

    /**

     * Given a trie, returns the "completion list" for a prefix, i.e. all the leaf nodes in the 

     * trie whose words start with this prefix. 

     * For instance, if the trie had the words "bear", "bull", "stock", and "bell",

     * the completion list for prefix "b" would be the leaf nodes that hold "bear", "bull", and "bell"; 

     * for prefix "be", the completion would be the leaf nodes that hold "bear" and "bell", 

     * and for prefix "bell", completion would be the leaf node that holds "bell". 

     * (The last example shows that an input prefix can be an entire word.) 

     * The order of returned leaf nodes DOES NOT MATTER. So, for prefix "be",

     * the returned list of leaf nodes can be either hold [bear,bell] or [bell,bear].

     *

     * @param root Root of Trie that stores all words to search on for completion lists

     * @param allWords Array of words that have been inserted into the trie

     * @param prefix Prefix to be completed with words in trie

     * @return List of all leaf nodes in trie that hold words that start with the prefix, 

     *             order of leaf nodes does not matter.

     *         If there is no word in the tree that has this prefix, null is returned.

     */

    public static ArrayList<TrieNode> completionList(TrieNode root,String[] allWords, String prefix) {
                 
    	
    	          try {
                  if(allWords.length == 0) {

                      return null;

                  }

                  TrieNode ptr = new TrieNode(null,null,null);

                  ptr = root;

                  if(ptr.substr == null) {

                  ptr= ptr.firstChild;

                  }

                  ArrayList<TrieNode> result = new ArrayList<TrieNode>();

                    /////////



                    String prefixyy = prefix;

                    /////////



                    while(ptr != null) {

                        String word = allWords[ptr.substr.wordIndex];

                        word = word.substring(ptr.substr.startIndex, ptr.substr.endIndex + 1);

                        if(matchtill(prefixyy,word) == 0 ) {

                            ptr = ptr.sibling;
                            continue;

                        }

                        //if there is partial match

                        if((matchtill(prefixyy,word) > 0) && matchtill(prefixyy,word) < prefixyy.length()) {
                        

                        prefixyy = prefixyy.substring(matchtill(prefixyy,word), prefixyy.length());

                        

                        ptr = ptr.firstChild;


                        continue;

                        }

                        //if there is full match
                      
                        if(((matchtill(prefixyy,word) > 0) && matchtill(prefixyy,word) == prefixyy.length())) {

                            result.addAll(addallNodesbelow(ptr,allWords,prefix)); 

                            break;
                        }                                  
                    }

                 if(result.size() > 0) {

                 return result;

                 }else {

                  return null;

                  }
                   } 
               catch(NullPointerException ex)
               {
             	return null;
               }
                }
    
    

    public static ArrayList<TrieNode> addallNodesbelow(TrieNode root,String[] allWords, String prefix) {

        
        TrieNode ptr = new TrieNode(null,null,null);

        ptr = root;
        System.out.print("hey"+ptr.toString());
        if(ptr.substr == null) {

        ptr= ptr.firstChild;

        }

        ArrayList<TrieNode> result = new ArrayList<TrieNode>();

          /////////

          TrieNode firstchild = new TrieNode(null,null,null);

          firstchild = ptr.firstChild;

          TrieNode sibling = new TrieNode(null,null,null);

          sibling = ptr.sibling;

          String word = allWords[ptr.substr.wordIndex];
          /////////////
          if(doesTheyMatch(prefix,word)) {
              if(ptr.firstChild == null) {

              result.add(ptr);
                 
             if(word.length() == prefix.length()) {
            	 return result;
             }

              }

             }
          ////////////
          if(sibling != null ) {
                System.out.print("lol"+ptr.toString());
                result.addAll(addallNodesbelow(ptr.sibling, allWords, prefix)); 

          }

          /////////

         if(firstchild != null) {

                result.addAll(addallNodesbelow(ptr.firstChild, allWords, prefix)); 

         }




return result;

}
    public static boolean doesTheyMatch(String prefix,String Second) {

        int matchTill = 0; 

        for(int j = 0; j < prefix.length(); j++ ){

  

              if(Second.charAt(j)==prefix.charAt(j)) {

                  matchTill++;

                  continue;

              }else {

                  break;

            }

        }

        if(matchTill == prefix.length()) {

            return true;

        }else {

            return false;

               }

      

  }

    public static int matchtill(String prefix,String Second) {

        if(prefix == null || Second == null) {

            return 0;

        }

          int matchTill = 0; 

          for(int j = 0; j < prefix.length(); j++ ){

              if(j >= Second.length()) {

                  break;

              }

                if(Second.charAt(j)==prefix.charAt(j)) {

                    matchTill++;

                    continue;

                }else {

                    break;

              }

          }

         return matchTill;

    }

    public static void print(TrieNode root, String[] allWords) {

        System.out.println("\nTRIE\n");

        print(root, 1, allWords);

    }

    

    private static void print(TrieNode root, int indent, String[] words) {

        if (root == null) {

            return;

        }

        for (int i=0; i < indent-1; i++) {

            System.out.print("    ");

        }

        

        if (root.substr != null) {

            String pre = words[root.substr.wordIndex]

                            .substring(0, root.substr.endIndex+1);

            System.out.println("      " + pre);

        }

        

        for (int i=0; i < indent-1; i++) {

            System.out.print("    ");

        }

        System.out.print(" ---");

        if (root.substr == null) {

            System.out.println("root");

        } else {

            System.out.println(root.substr);

        }

        

        for (TrieNode ptr=root.firstChild; ptr != null; ptr=ptr.sibling) {

            for (int i=0; i < indent-1; i++) {

                System.out.print("    ");

            }

            System.out.println("     |");

            print(ptr, indent+1, words);

        }

    }

 }