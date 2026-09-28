# QuadTree Image Compression

A Java-based implementation of image representation and compression
using a QuadTree data structure.

## Overview

This project represents a grayscale image using a QuadTree. The image
is recursively divided into four regions, with leaf nodes representing
uniform regions and internal nodes representing regions that require
further subdivision.

The project also supports reconstructing the image from the QuadTree
representation at a specified depth.

## Features

- QuadTree-based image representation
- Recursive tree construction
- Grayscale image reconstruction
- Configurable reconstruction depth
- Recursive traversal of QuadTree nodes
- Java Swing-based image visualization
- Calculation of tree statistics such as nodes and leaves

## Data Structure

### QuadTree

Each internal QuadTree node is divided into four children:

- Upper-left
- Upper-right
- Lower-left
- Lower-right

Leaf nodes store grayscale values, while internal nodes recursively
store their four child regions.

## Technology

- Java
- QuadTree
- Recursion
- Java Swing

## Project Structure

```text
QuadTree-Image-Compression/
├── QuadTree.java
├── QtNode.java
├── TreeCreator.java
├── MyTreeCreator.java
└── FinalImage.txt
