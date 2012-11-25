(ns todo.formatters
  (:require [hiccup.core :as hiccup]
            [cheshire.core :as cheshire]))

(defn list-to-json [id items]
  "Given a list id and the contents of the list, generate JSON
  representing the list."
  (cheshire/generate-string {:list {:id id :items items}}))

(defn list-to-preload [id items]
  "Given a list id and the contents of the list, generate a preload JSON
  payload representing the list."
  (hiccup/html [:script {:id "preload"} (list-to-json id items)]))

(defn generate-application-template []
  "Generate the application template."
  (hiccup/html
    [:html {:lang "en"}
      [:head
       [:meta {:charset "utf-8"}]
       [:title "Todo"]
       [:script "var CLOSURE_NO_DEPS = true;"]]
      [:body
       [:script {:src "application.js"}]
       [:script {:src "todo.application.main();"}]]]))
