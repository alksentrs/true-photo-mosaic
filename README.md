# Photo Mosaic creator

This program can turn any image into a photo mosaic, which is an image made of lots of small images (tiles) and looks similar to the given image.

It provides a flexibility to choose a trade-off between fast execution (<1 second of tiles searching time) and more precise output image (~1 minute of tiles searching time).

There are three main components, provided as separate applications:

1. An application to read tiles from a local path (the images must have same size).
2. An application to convert the local dataset into a small tile dataset.
3. An application to builds a search index to the local images.
4. An application to built index to create photo mosaic for provided image.

Examples of the outputs produced by Photo Mosaic creator:

Avengers photo mosaic produced after fast execution:
![Alt](https://raw.githubusercontent.com/Refactula/photo-mosaic/master/examples/avengers-fast.png "Avengers photo mosaic fast")

Avengers photo mosaic produced after precise execution:
![Alt](https://raw.githubusercontent.com/Refactula/photo-mosaic/master/examples/avengers-precise.png "Avengers photo mosaic precise")

The Beatles album cover photo mosaic produced after fast execution:
![Alt](https://raw.githubusercontent.com/Refactula/photo-mosaic/master/examples/the-beatles-fast.png "The Beatles album cover photo mosaic fast")

The Beatles album cover photo mosaic produced after precise execution:
![Alt](https://raw.githubusercontent.com/Refactula/photo-mosaic/master/examples/the-beatles-precise.png "The Beatles album cover photo mosaic precise")

Cat photo mosaic:
![Alt](https://raw.githubusercontent.com/Refactula/photo-mosaic/master/examples/cat-precise.png "Cat photo mosaic")

Parrots photo mosaic:
![Alt](https://raw.githubusercontent.com/Refactula/photo-mosaic/master/examples/colorful-parrots-precise.png "Parrots photo mosaic")

Superman photo mosaic:
![Alt](https://raw.githubusercontent.com/Refactula/photo-mosaic/master/examples/output-precise.png "Superman photo mosaic")
