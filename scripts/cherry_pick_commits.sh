#!/bin/bash

# Variables
SOURCE_BRANCH="feature-branch"  # Branch from which to read commits
TARGET_BRANCH="main"            # Branch to which commits will be applied
NUMBER_OF_COMMITS=5              # Number of recent commits to copy

# Check out the target branch
git checkout $TARGET_BRANCH

# Get the list of recent commit hashes from the source branch
COMMITS=$(git log $SOURCE_BRANCH -n $NUMBER_OF_COMMITS --pretty=format:"%H")

# Apply each commit to the target branch
for COMMIT in $COMMITS; do
  git cherry-pick $COMMIT
done

# Push changes to the remote repository
git push origin $TARGET_BRANCH
