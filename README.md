# QuadTree Image Compression

A Java-based image representation and compression project using a
QuadTree data structure to recursively represent grayscale images.

## Overview

This project uses a QuadTree to represent a grayscale image by
recursively dividing the image into four regions.

Uniform regions are stored as leaf nodes with a grayscale value,
while non-uniform regions are recursively divided into four child
nodes.

The project also supports reconstructing the image from the QuadTree
at different depths, allowing different levels of detail to be
visualized.

## Features

- QuadTree-based grayscale image representation
- Recursive tree construction
- Recursive traversal of QuadTree nodes
- Image reconstruction from the tree
- Configurable reconstruction depth
- Grayscale image visualization using Java Swing
- File-based input for QuadTree data
- Tracking of nodes and leaf nodes
- Calculation of grayscale values for internal nodes

## Data Structure

### QuadTree

A QuadTree recursively divides a two-dimensional region into four
subregions:

- Upper-left
- Upper-right
- Lower-left
- Lower-right

Each node is either:

- **Leaf node** — stores a grayscale value
- **Internal node** — contains four child nodes

This hierarchical representation allows different regions of an
image to be represented at different levels of detail.

## Technology

- **Java**
- **QuadTree Data Structure**
- **Recursion**
- **Java Swing**
- **File I/O**

## Project Structure

```text
QuadTree-Image-Compression/
│
├── QuadTree.java
├── QtNode.java
├── TreeCreator.java
├── MyTreeCreator.java
└── FinalImage.txt
