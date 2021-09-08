package duke.listener;

import duke.constant.MessageType;

/**
 * The is the Message interface.
 */
public interface Message {

    /**
     * Shows messages.
     *
     * @param messageType Message type.
     * @param messages Multiple message strings or messages array.
     */
    public void show(MessageType messageType, String... messages);
}
