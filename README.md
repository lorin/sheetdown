# sheetdown

Converts a Google Sheets range in your clipboard to (Github Flavored) Markdown [table] format.

[table]: https://github.github.com/gfm/#tables-extension-

## Example input

![screen shot](gsheet.png)

## Generated output

### Rendered

| Multi-region data | (ver, acc, region) entry in cache | cached image is older than river data | prime cache? |
| ----------------- | --------------------------------- | ------------------------------------- | ------------ |
|                 T |                                 T |                                     T |            T |
|                 T |                                 T |                                     F |            F |
|                 T |                                 F |                                     * |            T |
|                 F |                                 * |                                     * |            F |


### Literal

```
| Multi-region data | (ver, acc, region) entry in cache | cached image is older than river data | prime cache? |
| ----------------- | --------------------------------- | ------------------------------------- | ------------ |
|                 T |                                 T |                                     T |            T |
|                 T |                                 T |                                     F |            F |
|                 T |                                 F |                                     * |            T |
|                 F |                                 * |                                     * |            F |
```


## Usage

Currently, you need [Leiningen] to run it.

[Leiningen]: https://leiningen.org/


1. Copy a Google Sheets range to your clipboard.
2. Run the command:

    $ lein run 
    

Eventually, if there's demand for building a standalone jar, it'll run like this:
    
    $ java -jar sheetdown-1.0.0-standalone.jar


## Notes

Only tested on macOS with Google Chrome.

