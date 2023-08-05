#!/bin/bash
if [ ! -f "/home/circleci/repo/api_authorization_check_result.json" ];then
  echo 'haha'
  exit 1
fi
