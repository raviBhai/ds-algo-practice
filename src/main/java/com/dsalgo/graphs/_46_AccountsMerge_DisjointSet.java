package com.dsalgo.graphs;

import java.util.*;

/*

Given list of names along with list of email ids with each name.
Few names can have few email ids as common. It means, all the unique email ids under these names belong to the same account.
Merge these mail ids under the same account.
After merging, the mail ids should be sorted.
Return this data as response.

Example -
{ "John", "J1@com", "J2@com", "J3@com" }
{ "John", "J4@com" }
{ "Raj", "R1@com", "R2@com" }
{ "John", "J1@com", "J5@com" }
{ "Raj", "R2@com", "R3@com" }
{ "Mary", "M1@com" }

1st and 4th have J1@com as common
3rd and 5th have R2@com as common

After merging and sorting, we get the below and need to return it  :
{ "John", "J1@com", "J2@com", "J3@com", "J5@com" }
{ "John", "J4@com" }
{ "Raj", "R1@com", "R2@com", "R3@com" }
{ "Mary", "M1@com" }

Solution :
As the graph configuration changes with each edge, we can use Disjoint set.

Algorithm :
The example has 6 entries, consider each as node from 0 to 5

0 -> { "John", "J1@com", "J2@com", "J3@com" }
1 -> { "John", "J4@com" }
2 -> { "Raj", "R1@com", "R2@com" }
3 -> { "John", "J1@com", "J5@com" }
4 -> { "Raj", "R2@com", "R3@com" }
5 -> { "Mary", "M1@com" }

Init disjoint set with 6 nodes.
Each will be its own parent.

We will traverse over these, and when we find a common email, we will do union() of the nodes.

To find the common/overlap, we need to check if we have encountered the email id previously.
It means, we need to store email ids in a map.
Create a map with string, integer
key : email
value : node

Start iterating from node 0 to 5.
Skip the name, and read the email ids.
If the email id is not present in the map, perform map.put(email, currentNode)
If the email id is present, there is an overlap, so get existing node under which the email is already present, and then add the currentNode under the existing node.

Dry run :
Get first entry - 0 -> { "John", "J1@com", "J2@com", "J3@com" }
Skip name and get the email ids.

Map -
J1@com - 0
J2@com - 0,
J3@com - 0

Similarly, execute it for 2nd and 3rd

Map -
J1@com - 0
J2@com - 0,
J3@com - 0,
J4@com - 1,
R1@com - 2,
R2@com - 2,

Now, we get the entry : 3 -> { "John", "J1@com", "J5@com" }
J1@com already present in the map, hence, add the currentNode 3 under existingNode 0
J5@com not present, so add it to the map.

Map -
J1@com - 0
J2@com - 0,
J3@com - 0,
J4@com - 1,
R1@com - 2,
R2@com - 2,
J5@com - 3,

Now we process the entry : 4 -> { "Raj", "R2@com", "R3@com" }
R2@com already present hence add 4 under 2


Map -
J1@com - 0
J2@com - 0,
J3@com - 0,
J4@com - 1,
R1@com - 2,
R2@com - 2,
J5@com - 3,
R3@com - 4
M1@com - 5

At this point, Disjoint set would be like this :
0----3, 1, 2----4, 5

Now we want to club the email ids under the same ultimate parent together.
We can define an array of size n, let's say indexArray.
Each entry of this array will be a list of string, as it will store the email ids.

We iterate over the map, get the value, and get its ultimate parent, and then add the key to indexArray[ulp_of_value]

indexArray -
{
    { },
    { },
    { },
    { },
    { },
    { }
}

First 3 entries has ulp as 0, so indexArray will be -
indexArray -
{
    { "J1@com", "J2@com", "J3@com" },
    { },
    { },
    { },
    { },
    { }
}

Process next 3 entries from the map -
indexArray -
{
    { "J1@com", "J2@com", "J3@com" },
    { "J4@com" },
    { "R1@com", "R2@com" },
    { },
    { },
    { }
}

Next we get this entry from the map : J5@com - 3
ulp of 3 is 0, so we add J5@com to index 0
indexArray -
{
    { "J1@com", "J2@com", "J3@com", "J5@com" },
    { "J4@com" },
    { "R1@com", "R2@com" },
    { },
    { },
    { }
}

similarly, process last  entries from the map

indexArray -
{
    { "J1@com", "J2@com", "J3@com", "J5@com" },
    { "J4@com" },
    { "R1@com", "R2@com", "R3@com" },
    { },
    { },
    { "M1@com" }
}

Now, to associate the email ids with the names, we can get the name at index 0 from the input and the email ids at index 0 from the above structure.
Similarly, we can do this for other entries


Return this :

{ "John", "J1@com", "J2@com", "J3@com", "J5@com" }
{ "John", "J4@com" }
{ "Raj", "R1@com", "R2@com", "R3@com" }
{ "Mary", "M1@com" }


 */
public class _46_AccountsMerge_DisjointSet {

    private static List<List<String>> solve(List<List<String>> input) {
        DisjointSet ds = new DisjointSet(input.size());
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            List<String> emailIdsWithName = input.get(i);

            for (int j = 1; j < emailIdsWithName.size(); j++) {
                String email = emailIdsWithName.get(j);
                if (map.containsKey(email)) {
                    int existingNode = map.get(email);
                    ds.unionBySize(existingNode, i);    // i is the currentNode
                } else {
                    map.put(email, i);
                }
            }
        }

        List<String>[] indexArray = new ArrayList[input.size()];
        for (int i = 0; i < input.size(); i++) {
            indexArray[i] = new ArrayList<>();
        }

        for (String key : map.keySet()) {
            Integer value = map.get(key);
            int ulp = ds.findParent(value);
            indexArray[ulp].add(key);
        }

        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < indexArray.length; i++) {
            if (indexArray[i].isEmpty()) {
                continue;
            }

            List<String> temp = new ArrayList<>();  // this will hold name, email_id1, email_id2, email_id3
            temp.add(input.get(i).get(0));    // get name from input which will be present at the same index, and add it to the result
            Collections.sort(indexArray[i]);
            temp.addAll(indexArray[i]);
            ans.add(temp);
        }

        return ans;
    }

    public static void main(String[] args) {
        /*

            { "John", "J1@com", "J2@com", "J3@com" }
            { "John", "J4@com" }
            { "Raj", "R1@com", "R2@com" }
            { "John", "J1@com", "J5@com" }
            { "Raj", "R2@com", "R3@com" }
            { "Mary", "M1@com" }

         */

        int n = 6;
        List<List<String>> input = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            input.add(new ArrayList<>());
        }
        input.get(0).add("John");
        input.get(0).add("J1@com");
        input.get(0).add("J2@com");
        input.get(0).add("J3@com");

        input.get(1).add("John");
        input.get(1).add("J4@com");

        input.get(2).add("Raj");
        input.get(2).add("R1@com");
        input.get(2).add("R2@com");

        input.get(3).add("John");
        input.get(3).add("J1@com");
        input.get(3).add("J5@com");

        input.get(4).add("Raj");
        input.get(4).add("R2@com");
        input.get(4).add("R3@com");

        input.get(5).add("Mary");
        input.get(5).add("M1@com");

        System.out.println(solve(input));
    }
}
