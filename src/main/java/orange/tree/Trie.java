/*
 * Copyright 2006-2018 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package orange.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created 4/2/2019
 *
 * @author sjkumar
 */
public class Trie {

    static class Node{
        private Character value;
        private Node [] children = new Node[26];
        private boolean isCompleteWord = false;

        Node(Character value) {
            this.value = value;
        }

        Character getValue() {
            return value;
        }

        boolean isValueEqual(Node other){
            return other.getValue() == value;
        }

        Optional<Node> getChildNodeContaining(Character ch){
            ch = Character.toLowerCase(ch);
            int placeValue = ch - 'a';
            return Optional.ofNullable(children[placeValue]);
        }

        void addChild(Node child){
            Character character = child.getValue();
            int placeValue = Character.toLowerCase(character) - 'a';
            if (children[placeValue] != null){
                throw new RuntimeException("Trying to rewrite a node's value.");
            }
            children[placeValue] = child;
        }

        boolean isCompleteWord() {
            return isCompleteWord || allChildrenNull();
        }

        boolean allChildrenNull(){
            return Arrays.stream(children).allMatch(Objects::isNull);
        }

        void setCompleteWord(boolean completeWord) {
            isCompleteWord = completeWord;
        }
    }

    private Node root;

    public Trie() {
        root = new Node(' ');
    }

    public Trie add(String string){
        addRecursively(root, string);
        return this;
    }
    private void addRecursively(Node subRoot, String string){
        if ("".equals(string)){
            return;
        }
        Character firstChar = string.charAt(0);
        Optional<Node> child = subRoot.getChildNodeContaining(firstChar);

        if (child.isPresent()){
            addRecursively(child.get(), string.substring(1));
        }else{
            child = Optional.of(new Node(firstChar));
            if (string.length() == 1) child.get().setCompleteWord(true);
            addRecursively(child.get(), string.substring(1));
            subRoot.addChild(child.get());
        }
    }

    List<String> getAll(String prefix){
        return getAll(traverseUpto(root, prefix), prefix.substring(0, prefix.length() -1));
    }

    private List<String> getAll(Node subRoot){
        return getAll(subRoot, "");
    }

    private List<String> getAll(Node subRoot, String prefix){
        List<String> allStrings = new ArrayList<>();
        if (subRoot == null){
            return Collections.singletonList("");
        }
        if (subRoot.isCompleteWord()){
            allStrings.add(prefix + subRoot.getValue());
        }
        allStrings.addAll(Arrays.stream(subRoot.children)
                .filter(Objects::nonNull)
                .map(this::getAll)
                .flatMap(Collection::stream)
                .map(str-> prefix + subRoot.getValue() + str)
                .collect(Collectors.toList()));
        return allStrings;
    }

    private Node traverseUpto(Node subTree, String prefix){
        if ("".equals(prefix)){
            return subTree;
        }
        if (subTree == null){
            return null;
        }
        return traverseUpto(subTree.getChildNodeContaining(prefix.charAt(0)).orElse(null), prefix.substring(1));
    }
}
