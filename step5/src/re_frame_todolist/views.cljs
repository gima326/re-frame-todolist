(ns re-frame-todolist.views
  (:require
   [re-frame.core :as re-frame]
   [re-frame-todolist.subs :as subs]

   ;; step2
   [re-frame-todolist.events :as events]
   [clojure.string :as cstr]
   [reagent.core :as reagent]

   ;; step5
   [reagent.dom :as rdom]

   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     ]))

;; step1

;; (defn list-todo-item [todo]
;;  [:li (:title todo)])

;; step3

(defn list-todo-item [{:keys [id completed title]}]
  [:li
   [:input {:type "checkbox"
            :class "toggle"
            :checked completed
            :on-change #(re-frame/dispatch [::events/toggle id])}]

   [:span {:class (if completed "completed")}
    title]

   ;; step4
   [:button {:on-click #(re-frame/dispatch [::events/delete id])}
    "Delete"]
   ])

;; step2

(def KEY-CODE-ENTER 13)

(defn todo-input []
  (let [val (reagent/atom "")]
    (fn []
      [:input {:type        "text"
               :value       @val
               :class       "new-todo"
               :placeholder "What needs to be done?"
               :on-change   #(reset! val (-> % .-target .-value))
               :on-key-down #(if (= (.-which %) KEY-CODE-ENTER)
                               (let [title (-> @val cstr/trim)]

                                 ;; (seq "abc") => (\a \b \c)
                                 ;; (seq "")    => nil

                                 (if (seq title)
                                   (re-frame/dispatch [::events/add-todo title]))

                                 ;; 入力欄を初期化
                                 (reset! val "")))}])))

;; step5

(defn todo-input2 [{:keys [title on-save on-stop]}]
  (let [val (reagent/atom title)
        stop #(do (reset! val "")
                  (if on-stop (on-stop)))
        save #(let [new-title (-> @val cstr/trim)]
                (if (seq new-title)
                  (on-save new-title))
                (stop))]

    (fn [{:keys [id class placeholder]}]
      [:input {:type "text"
               :value @val
               :class class
               :placeholder placeholder
               :on-blur save
               :on-change #(reset! val (-> % .-target .-value))
               :on-key-down #(if (= (.-which %) KEY-CODE-ENTER)
                               (save))}])
    ))

(def todo-edit
  (with-meta todo-input2
    {:component-did-mount #(.focus (rdom/dom-node %))}))

(defn list-todo-item2 []

  ;; 行ごとに状態（表示、編集）を持たせるためには、let over lambda 形式にしなきゃいけない。
  (let [editing (reagent/atom false)]
    (fn [{:keys [id completed title]}]
      [:li {:class (if completed "completed")}

       [:div
        [:input {:type "checkbox"
                 :class "toggle"
                 :checked completed
                 :on-change #(re-frame/dispatch [::events/toggle id])}]

        ;; 追加２
        (if (and @editing (not completed))
          ;; 編集モード
          [todo-edit
           ;;todo-input2
           {:class "edit"
            :title title
            :on-save #(re-frame/dispatch [::events/update id %])
            :on-stop #(reset! editing false)}]

          ;; 表示モード
          [:label {:on-double-click #(reset! editing true)} title])

        (if completed
          ;; 削除ボタン
          [:button
           {:on-click
            (fn []
              (if (js/window.confirm "Are you sure you want to delete this?")
                (re-frame/dispatch [::events/delete id])))} "Delete"]
          )
        ]
       ])))

;; (defn todo-list []
;;   [:ul
;;    (map
;;     (fn [[idx todo]] ^{:key (:id todo)} [list-todo-item todo])
;;     @(re-frame/subscribe [::subs/todos]))])

(defn todo-list []
  [:div

   ;; 入力欄
   [todo-input]

   [:ul
    (map
     (fn [[idx todo]] ^{:key (:id todo)} [list-todo-item todo])
     @(re-frame/subscribe [::subs/todos]))]])

;; step5

(defn todo-list2 []
  [:div
   ;; 変更
   [todo-input2 {:class "new-todo"
                 :placeholder "What needs to be done?"
                 :on-save #(re-frame/dispatch [::events/add-todo %])}]

   [:ul
    (map
     (fn [[idx todo]] ^{:key (:id todo)} [list-todo-item2 todo])
     @(re-frame/subscribe [::subs/todos]))

    [:p "Double-click to edit a todo."]]])
