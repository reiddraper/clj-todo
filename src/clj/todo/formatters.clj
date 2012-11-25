(ns todo.formatters
  (:require [hiccup.core :as hiccup]
            [cheshire.core :as cheshire]))

(defn list-json-serializer [list-id list-contents]
  "Given a list id and the contents of the list, generate JSON
  representing the list."
  (cheshire/generate-string {:list {:id list-id
                                    :items list-contents}}))

(defn lists-json-serializer [lists]
  "Given a series of lists, generate JSON representing the lists."
  (cheshire/generate-string {:lists lists}))

(defn- prepare-preload [json]
  "Prepare preload data by wrapping in a IIFE."
  (str "var preload = (function() { return JSON.parse(\"" json "\"); })();"))

(defn generate-application-template [preload]
  "Generate the application template."
  (hiccup/html
    [:html {:lang "en"}
      [:head
       [:meta {:charset "utf-8"}]
       [:title "Todo"]
       [:script {:type "text/javascript"} "var CLOSURE_NO_DEPS = true;"]]
      [:body
       [:script {:type "text/javascript"
                 :src "application.js"}]
       [:script {:type "text/javascript"
                 :id "preload"} (prepare-preload preload)]
       [:script {:type "text/javascript"} "todo.application.main();"]]]))
