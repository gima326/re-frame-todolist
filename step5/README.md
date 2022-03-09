# Step5: TODO 編集

### 〜「Step5」 でやっていること〜

・各 TODO 項目をダブルクリックすることで、既存の文言を編集できるようにする。<br>
　「Step2」で追加した登録処理と、編集処理をを統合する

・細かな挙動を変更。<br>
　削除ボタンの表示条件（「完了」した項目のみ、ボタンを表示する）<br>
　削除ボタン押下時に、一旦 dialog を表示する（「本当に削除するの？（Y/N）」）<br>

### 〜変更対象ファイル〜

[ ~/src/re_frame_todolist/ ]<br>

　・core.cljs<br>
　・event.cljs<br>
　・views.cljs<br>

　※以下のファイル群は数が多いのでアップロードしてません。<br>

　　[ ~/node_modules/* ]<br>
　　[ ~/resource/public/js/compiled/* ]<br>

### 〜編集〜
![todo](https://github.com/gima326/re-frame-todolist/blob/main/readme_img/step5-1.png)
![todo](https://github.com/gima326/re-frame-todolist/blob/main/readme_img/step5-2.png)

### 〜確認〜
![todo](https://github.com/gima326/re-frame-todolist/blob/main/readme_img/step5-3.png)
![todo](https://github.com/gima326/re-frame-todolist/blob/main/readme_img/step5-4.png)
