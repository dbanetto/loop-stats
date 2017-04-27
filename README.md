# Loop Statistics 

A tool to gather statistics about the usages of loops in java programs.

## What is going to be collected

Conventional loops (`for` and `while` loops) will be checked for some traits:

 * Does it contain branches? e.g `if`'s
 * Does it have early exit points? e.g. `break` or `continue`
 * Does it have nested loops?
 * Is it using variables from outer scope?

## What is it for

This project is to get some rough idea on the complexity of 
loops in practice so I can focus my Honours project to effective methods,
generating simple loop invariants.

## License

This project is licensed under Apache 2, see [LICESNE](./LICESNE) for
more details.
