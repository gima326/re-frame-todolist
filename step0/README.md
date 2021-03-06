# Step0: プロジェクト作成

### 〜「Step0」 でやっていること〜

・re-frame プロジェクトを作成する。<br>
・実行に必要な、不足しているライブラリへの参照を追加する。<br>
・[ http://localhost:8280/ ] にアクセスして、"Hello from re-frame" が表示されることを確認する。<br>

![todo](https://github.com/gima326/re-frame-todolist/blob/main/readme_img/step0.png)

```sh
$ lein new re-frame re-frame-todolist

Generating re-frame project.
```

さっそく実行してみるも、エラーになる。

```sh
$ cd re-frame-todolist
$ npx shadow-cljs watch app
...

The required namespace "react-dom" is not available, it was required by "reagent/dom.cljs".
```

エラーを解消するべく、参照を追加する。<br>
npm コマンドを実行したタイミングで、ディレクトリ「~/node_modules」が生成され、その配下に JS ファイルがたんまり生成される。<br>
100 項目、3.7 MB 分くらいのファイル群になる。<br>

```sh
$ npm install react-dom
...

re-frame-todolist@ ~/re-frame-todolist
├── UNMET DEPENDENCY react@17.0.2　　        <-- ここがマズいみたい
└─┬ react-dom@17.0.2
  ├─┬ loose-envify@1.4.0
  │ └── js-tokens@4.0.0
  ├── object-assign@4.1.1
  └── scheduler@0.20.2

npm WARN react-dom@17.0.2 requires a peer of react@17.0.2 but none was installed.

```

…まだなにか問題があるみたい。

```sh
$ npx shadow-cljs watch app
...

The required JS dependency "react" is not available, 
it was required by "node_modules/react-dom/cjs/react-dom.production.min.js".
```

さらに参照追加してみて、様子をみる。

```sh
$ npm install react
```

"app" は Build id で、[ shadow-cljs.edn ]に記載しているものと一致させる必要がある。<br>

```sh
$ npx shadow-cljs watch app

npx: installed 97 in 8.678s
shadow-cljs - config: ~/shadow-cljs.edn
shadow-cljs - updating dependencies
shadow-cljs - dependencies updated
shadow-cljs - HTTP server available at http://localhost:8280
shadow-cljs - HTTP server available at http://localhost:8290
shadow-cljs - server version: 2.17.8 running at http://localhost:9630
shadow-cljs - nREPL server started on port 8777
shadow-cljs - watching build :app
[:app] Configuring build.
[:app] Compiling ...
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[:app] Build completed. (203 files, 202 compiled, 0 warnings, 14.44s)
```

コンパイルが完了すると、ディレクトリ「~/resource/public/js/compiled」が生成され、その配下にファイルがたんまり生成される。<br>
411 項目、18.6 MB 分くらいのファイル群になる。<br><br>

以下、プロジェクト作成時に、自動生成された README.md の内容。<br><br>

# re-frame-todolist

A [re-frame](https://github.com/day8/re-frame) application designed to ... well, that part is up to
you.

## Getting Started

### Project Overview

* Architecture:
[Single Page Application (SPA)](https://en.wikipedia.org/wiki/Single-page_application)
* Languages
  - Front end is [ClojureScript](https://clojurescript.org/) with ([re-frame](https://github.com/day8/re-frame))
* Dependencies
  - UI framework: [re-frame](https://github.com/day8/re-frame)
  ([docs](https://github.com/day8/re-frame/blob/master/docs/README.md),
  [FAQs](https://github.com/day8/re-frame/blob/master/docs/FAQs/README.md)) ->
  [Reagent](https://github.com/reagent-project/reagent) ->
  [React](https://github.com/facebook/react)
* Build tools
  - CLJS compilation, dependency management, REPL, & hot reload: [`shadow-cljs`](https://github.com/thheller/shadow-cljs)
* Development tools
  - Debugging: [CLJS DevTools](https://github.com/binaryage/cljs-devtools)

#### Directory structure

* [`/`](/../../): project config files
* [`dev/`](dev/): source files compiled only with the [dev](#running-the-app) profile
  - [`user.cljs`](dev/cljs/user.cljs): symbols for use during development in the
[ClojureScript REPL](#connecting-to-the-browser-repl-from-a-terminal)
* [`resources/public/`](resources/public/): SPA root directory;
[dev](#running-the-app) / [prod](#production) profile depends on the most recent build
  - [`index.html`](resources/public/index.html): SPA home page
    - Dynamic SPA content rendered in the following `div`:
        ```html
        <div id="app"></div>
        ```
    - Customizable; add headers, footers, links to other scripts and styles, etc.
  - Generated directories and files
    - Created on build with either the [dev](#running-the-app) or [prod](#production) profile
    - `js/compiled/`: compiled CLJS (`shadow-cljs`)
      - Not tracked in source control; see [`.gitignore`](.gitignore)
* [`src/re_frame_todolist/`](src/re_frame_todolist/): SPA source files (ClojureScript,
[re-frame](https://github.com/Day8/re-frame))
  - [`core.cljs`](src/re_frame_todolist/core.cljs): contains the SPA entry point, `init`
* [`.github/workflows/`](.github/workflows/): contains the
[github actions](https://github.com/features/actions) pipelines.
  - [`test.yaml`](.github/workflows/test.yaml): Pipeline for testing.


### Editor/IDE

Use your preferred editor or IDE that supports Clojure/ClojureScript development. See
[Clojure tools](https://clojure.org/community/resources#_clojure_tools) for some popular options.

### Environment Setup

1. Install [JDK 8 or later](https://openjdk.java.net/install/) (Java Development Kit)
2. Install [Node.js](https://nodejs.org/) (JavaScript runtime environment) which should include
   [NPM](https://docs.npmjs.com/cli/npm) or if your Node.js installation does not include NPM also install it.
5. Clone this repo and open a terminal in the `re-frame-todolist` project root directory

### Browser Setup

Browser caching should be disabled when developer tools are open to prevent interference with
[`shadow-cljs`](https://github.com/thheller/shadow-cljs) hot reloading.

Custom formatters must be enabled in the browser before
[CLJS DevTools](https://github.com/binaryage/cljs-devtools) can display ClojureScript data in the
console in a more readable way.

#### Chrome/Chromium

1. Open [DevTools](https://developers.google.com/web/tools/chrome-devtools/) (Linux/Windows: `F12`
or `Ctrl-Shift-I`; macOS: `⌘-Option-I`)
2. Open DevTools Settings (Linux/Windows: `?` or `F1`; macOS: `?` or `Fn+F1`)
3. Select `Preferences` in the navigation menu on the left, if it is not already selected
4. Under the `Network` heading, enable the `Disable cache (while DevTools is open)` option
5. Under the `Console` heading, enable the `Enable custom formatters` option

#### Firefox

1. Open [Developer Tools](https://developer.mozilla.org/en-US/docs/Tools) (Linux/Windows: `F12` or
`Ctrl-Shift-I`; macOS: `⌘-Option-I`)
2. Open [Developer Tools Settings](https://developer.mozilla.org/en-US/docs/Tools/Settings)
(Linux/macOS/Windows: `F1`)
3. Under the `Advanced settings` heading, enable the `Disable HTTP Cache (when toolbox is open)`
option

Unfortunately, Firefox does not yet support custom formatters in their devtools. For updates, follow
the enhancement request in their bug tracker:
[1262914 - Add support for Custom Formatters in devtools](https://bugzilla.mozilla.org/show_bug.cgi?id=1262914).

## Development

### Running the App

Start a temporary local web server, build the app with the `dev` profile, and serve the app,
browser test runner and karma test runner with hot reload:

```sh
npm install
npx shadow-cljs watch app
```

Please be patient; it may take over 20 seconds to see any output, and over 40 seconds to complete.

When `[:app] Build completed` appears in the output, browse to
[http://localhost:8280/](http://localhost:8280/).

[`shadow-cljs`](https://github.com/thheller/shadow-cljs) will automatically push ClojureScript code
changes to your browser on save. To prevent a few common issues, see
[Hot Reload in ClojureScript: Things to avoid](https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html#things-to-avoid).

Opening the app in your browser starts a
[ClojureScript browser REPL](https://clojurescript.org/reference/repl#using-the-browser-as-an-evaluation-environment),
to which you may now connect.

#### Connecting to the browser REPL from your editor

See
[Shadow CLJS User's Guide: Editor Integration](https://shadow-cljs.github.io/docs/UsersGuide.html#_editor_integration).
Note that `npm run watch` runs `npx shadow-cljs watch` for you, and that this project's running build ids is
`app`, `browser-test`, `karma-test`, or the keywords `:app`, `:browser-test`, `:karma-test` in a Clojure context.

Alternatively, search the web for info on connecting to a `shadow-cljs` ClojureScript browser REPL
from your editor and configuration.

For example, in Vim / Neovim with `fireplace.vim`
1. Open a `.cljs` file in the project to activate `fireplace.vim`
2. In normal mode, execute the `Piggieback` command with this project's running build id, `:app`:
    ```vim
    :Piggieback :app
    ```

#### Connecting to the browser REPL from a terminal

1. Connect to the `shadow-cljs` nREPL:
    ```sh
    lein repl :connect localhost:8777
    ```
    The REPL prompt, `shadow.user=>`, indicates that is a Clojure REPL, not ClojureScript.

2. In the REPL, switch the session to this project's running build id, `:app`:
    ```clj
    (shadow.cljs.devtools.api/nrepl-select :app)
    ```
    The REPL prompt changes to `cljs.user=>`, indicating that this is now a ClojureScript REPL.
3. See [`user.cljs`](dev/cljs/user.cljs) for symbols that are immediately accessible in the REPL
without needing to `require`.

### Running `shadow-cljs` Actions

See a list of [`shadow-cljs CLI`](https://shadow-cljs.github.io/docs/UsersGuide.html#_command_line)
actions:
```sh
npx shadow-cljs --help
```

Please be patient; it may take over 10 seconds to see any output. Also note that some actions shown
may not actually be supported, outputting "Unknown action." when run.

Run a shadow-cljs action on this project's build id (without the colon, just `app`):
```sh
npx shadow-cljs <action> app
```
### Debug Logging

The `debug?` variable in [`config.cljs`](src/cljs/re_frame_todolist/config.cljs) defaults to `true` in
[`dev`](#running-the-app) builds, and `false` in [`prod`](#production) builds.

Use `debug?` for logging or other tasks that should run only on `dev` builds:

```clj
(ns re-frame-todolist.example
  (:require [re-frame-todolist.config :as config])

(when config/debug?
  (println "This message will appear in the browser console only on dev builds."))
```

## Production

Build the app with the `prod` profile:

```sh
npm install
npm run release
```

Please be patient; it may take over 15 seconds to see any output, and over 30 seconds to complete.

The `resources/public/js/compiled` directory is created, containing the compiled `app.js` and
`manifest.edn` files.
