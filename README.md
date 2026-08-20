# Warehouse Inventory & Order Priority System

## Application Theory Questions

### Binary Search Tree

#### Why does an inorder traversal of a BST return sorted results?

An inorder traversal goes through the left side of the tree, then the current
node, and then the right side. In my BST lower priority orders are put on the
left and higher priority orders are put on the right. This makes it so going
through the tree in that order gives me the priorities from lowest to highest.

#### What happens to the tree if you insert values in order (1, 2, 3, 4, 5)? How does this affect performance?

The tree becomes unbalanced because every new value is greater than the previous one
so each value gets added to the right side. It would look kinda like a linked list.
This makes searching and inserting slower because the tree is no longer balanced.

#### What is the difference between average and worst-case time complexity for a BST?

The average time for searching, inserting, or finding a value in a balanced BST is
faster because the tree is organized in a way that makes it easier to find values.
The worst case happens when the tree becomes unbalanced like when values are added
in sorted order. In that case the program might have to go through almost every value
to find what it is looking for.

#### Where would you place duplicate priority values in your tree? Explain your choice.

I would place duplicate priority values on the right side of the tree. This lets every
order remain in the BST even when multiple orders have the same priority.

---

### Sorting Algorithm

#### Explain how your sorting algorithm works step-by-step using a small example.

I used Insertion Sort.

If the product prices are:

50, 20, 40, 10

Insertion Sort starts with the second value and checks it with the values before it.

1. 20 is checked with 50, so they switch places:
   20, 50, 40, 10

2. 40 is checked with 50 and moved before it:
   20, 40, 50, 10

3. 10 is checked with the values before it and moved to the beginning:
   10, 20, 40, 50

The result is sorted from the lowest price to the highest price.

#### What is the time complexity of your algorithm?

Insertion Sort can take longer when there are a lot of products because it may have
to compare each product with the ones before it. If the products are already sorted
or mostly sorted, it can be much faster because fewer changes are needed.

#### When would your sorting algorithm perform well?

Insertion Sort performs well with small datasets or data that is already mostly sorted.

#### Why is your sorting algorithm ideal or not ideal for very large datasets?

Insertion Sort is not ideal for very large datasets because it can take a long time
when there are a lot of products. As the number of products increases, it has to do
more comparisons and move more products around. For a large amount of data, other
sorting methods would be faster.

---

### System Design

#### Why might you choose to sort data in your application instead of the database?

Sorting in the application lets me control how the products are sorted and also lets
me show the manual sorting part of the project. It also means I can sort the products
myself instead of having the database do it for me.

#### What is one advantage of using a BST in this system?

A BST provides an organized way to store orders based on their priority. It makes it
easy to find the lowest and highest priority orders and can return all orders in
priority order.

#### What is one limitation of your current design?

One limitation is that the BST can become unbalanced if orders are added in a certain
order. When this happens, it can start to work more like a linked list, which can make
it take longer to find or add orders.

---

## Unit Tests

The project includes three unit tests:

1. **BST Inorder Test** - verifies that orders are returned in sorted priority order.
2. **BST Highest/Lowest Test** - verifies that the highest and lowest priority orders are found correctly.
3. **Product Price Sorting Test** - verifies that products are correctly sorted by price using the manual Insertion Sort algorithm.

All 3/3 unit tests pass successfully.