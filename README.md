# RunLengthEncoding

An interview question I received:
 * Image Process - Run Length Encoding (RLE)
 * RLE algorithm to compress bitmap images. it is a lossless comparison algorithm which means the output can be turned back to the original output
 * 
 * Here is a sequence of bytes (8 bits) representing color information for a picture
 * 0 0 0 0 76 113 113 24 ...[298x] 24
 * 
 * The encoding is as follows:
 * First byte is the color (0-255), 0 as Black and 255 as White
 * Second byte is the length of the run
 * If the run is longer than 255,t hen repeat the encoding as a series of shorter runs, the last being shorter than 255
 * 
 * For the above sequence the output becomes
 * C n C  n C   n C  n   C  n
 * 0 4 76 1 113 2 24 255 24 45
