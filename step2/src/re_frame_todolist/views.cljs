(ns re-frame-todolist.views
  (:require
   [re-frame.core :as re-frame]
   [re-frame-todolist.subs :as subs]

   ;; step2
   [re-frame-todolist.events :as events]
   [clojure.string :as cstr]
   [reagent.core :as reagent]

   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     ]))

;; step1

(defn list-todo-item [todo]
  [:li (:title todo)])

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
