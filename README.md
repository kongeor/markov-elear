# markov-elear

This project is based on the [markov-elear](https://github.com/gigasquid/markov-elear) project created by Carin Meier,
for [this](https://howistart.org/posts/clojure/1) tutorial.

## How to use this repo

Checkout the `m1` tag, edit the `generator_test.clj` file and
uncomment the first test (remove the `#_`).

Change to project folder and run:

```bash
lein test
```

you should see a failing test.

Try to implement the first function.

Recur.

## Tips

Checkout the `m1h` tag which has hints above each function.

Use `lein test-refresh` to have tests run continiously.

Setup [humane test output](https://github.com/pjstadig/humane-test-output) to have better test error output.

## Help! This is too hard for me ...

There are a lot of great free online guides to get you starting with
clojure.

The [brave clojure](http://www.braveclojure.com/) book is also
a great place to start :)

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
