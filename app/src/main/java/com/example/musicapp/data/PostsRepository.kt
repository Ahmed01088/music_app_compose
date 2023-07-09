package com.example.musicapp.data

import kotlinx.coroutines.flow.Flow

interface PostsRepository {

    /**
     * Get a specific JetNews post.
     */
    suspend fun getPost(postId: String?): Result<Post>

    /**
     * Get JetNews posts.
     */
    suspend fun getPostsFeed(): Result<PostsFeed>

    /**
     * Observe the current favorites
     */
    fun observeFavorites(): Flow<Set<String>>

    /**
     * Observe the posts feed.
     */
    fun observePostsFeed(): Flow<PostsFeed?>

    /**
     * Toggle a postId to be a favorite or not.
     */
    suspend fun toggleFavorite(postId: String)
}