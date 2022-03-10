### Re-frame 事始め

頭からドボンと飛び込むシリーズ。<br>
reagent、Re-frame…、その包含関係がまだよく分からないままですが。<br>
TODOリストを作成し Re-frame の使い方を確認する、という先達の例をベースに、いろいろいじって理解する。<br>

Step0: プロジェクト作成<br>
Step1: TODO リスト表示<br>
Step2: TODO 追加<br>
Step3: TODO 完了/取消し<br>
Step4: TODO 削除<br>
Step5: TODO 編集<br>

![todo](https://github.com/gima326/re-frame-todolist/blob/main/readme_img/todo_img.png)

In `src/re_frame_todolist`, there's a matching set of files (each small):
```
src/re_frame_todolist
├── config.cljs
├── core.cljs         <--- entry point, plus history
├── db.cljs           <--- data related  (data layer)
├── subs.cljs         <--- subscription handlers  (query layer)
├── views.cljs        <--- reagent  components (view layer)
└── events.cljs       <--- event handlers (control/update layer)
```

```
[ core.cljs ] ---> [ config.cljs ]
       ├──-------> [ views.cljs  ] ---> [ subs.cljs ]
       |                  |
       |                  ├──────────── [ re-frame/subscribe () ]
       |                  |                  | 【db/default-db を直接参照せず、
       |                  |                  | 　re-frame が管理している状態を参照し、subs.cljs の関数の引数として渡している】
       |                  v                  v
       └──-------> [ events.cljs ] ---> [ db.cljs ]
```

## References

- 「[Re−frame 入門][1]」<br>
[ `https://qiita.com/snufkon/items/1d409c984faaa3c390a1` ]<br>
- 「[Reagent: Minimalistic React for ClojureScript][2]」<br>
[ `https://reagent-project.github.io/` ]<br>

[1]: https://qiita.com/snufkon/items/1d409c984faaa3c390a1
[2]: https://reagent-project.github.io/
