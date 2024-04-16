package ru.asocial.games.catacombs;

public interface IGame {
    ResourcesManager getResourcesManager();

    void setResourceManager(ResourcesManager resourcesManager);

    void onLoad();

    IMessageService getMessagingService();
}
