# otm-website-tests

An automated test suite for the OpenTreeMap website.

## Usage

To execute the test suite, first ensure that you have a Java Virtual Machine installed, then install [Leiningen](http://leiningen.org/) and execute the following command:

```bash
$ lein midje
```

Optionally, use the `OTM_BASE_URL` environment variable to adjust the testing target:

```bash
$ OTM_BASE_URL=http://another-host:8080 lein midje
```

In order have Midje watch your source files for changes use:

```bash
$ lein midje :autotest
```
