package dev.gl.flagsandcapitals.gui;

import dev.gl.flagsandcapitals.models.PopupManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.IllegalComponentStateException;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author gl
 */
public class PopupWindow {

    public static void showPopupWindow(Component parent,
            String message,
            int width,
            int height,
            int pxlsDown,
            int fadeDurationMs,
            int visibleDurationMs) {
        JWindow popup = new JWindow(SwingUtilities.getWindowAncestor(parent));
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        label.setFont(new Font("Arial", Font.BOLD, 14));
        popup.getContentPane().add(label);
        popup.setSize(width, height);

        // place window below the parent component
        Point parentLocation = parent.getLocationOnScreen();
        int x = parentLocation.x + (parent.getWidth() / 2) - (width / 2); // centered by the parent component's middle
        int y = pxlsDown >= 0
                ? parentLocation.y + parent.getHeight() + pxlsDown // 5 pixels lower than the parent component
                : parentLocation.y + pxlsDown;
        popup.setLocation(x, y);

        // preparing before show the popup window
        popup.setOpacity(0F);
        popup.setVisible(true);

        // fade in
        int steps = 20;
        int delay = fadeDurationMs / steps;
        Timer fadeInTimer = new Timer(delay, null);
        fadeInTimer.addActionListener(new AbstractAction() {
            int step = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                float opacity = step / (float) steps;
                popup.setOpacity(Math.min(1f, opacity));
                if (step >= steps) {
                    fadeInTimer.stop();

                    // showing
                    new Timer(visibleDurationMs, new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ((Timer) e.getSource()).stop();

                            // fade out
                            int fadeOutSteps = 20;
                            int fadeOutDelay = fadeDurationMs / fadeOutSteps;
                            Timer fadeOutTimer = new Timer(fadeOutDelay, null);
                            fadeOutTimer.addActionListener(new AbstractAction() {
                                int outStep = 0;

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    outStep++;
                                    float opacity = 1f - (outStep / (float) fadeOutSteps);
                                    popup.setOpacity(Math.max(0F, opacity));
                                    if (outStep >= fadeOutSteps) {
                                        popup.setVisible(false);
                                        popup.dispose();
                                        ((Timer) e.getSource()).stop();
                                    }
                                }
                            });
                            fadeOutTimer.start();
                        }
                    }).start();
                }
            }
        });
        fadeInTimer.start();
    }

    public static void showPopupWindowWithManager(Component parent,
            ImageIcon icon,
            int width,
            int height,
            int pxlsDown,
            int fadeDurationMs,
            int visibleDurationMs,
            PopupManager manager,
            MainWindow mainWindow) throws IllegalComponentStateException {

        String message = manager.getMessageQueue().poll();
        if (message == null) {
            manager.setIsShowing(false);
            return;
        }

        manager.setIsShowing(true);

        JWindow popup = new JWindow(SwingUtilities.getWindowAncestor(parent));

        // parentPanel
        JPanel parentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        parentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        parentPanel.setBackground(Color.WHITE);

        // iconPanel
        JLabel imageLabel = new JLabel(icon);
        parentPanel.add(imageLabel);

        // textPanel
        JLabel textLabel = new JLabel(message, SwingConstants.CENTER);
        textLabel.setFont(new Font("Arial", Font.BOLD, 14));
        parentPanel.add(textLabel);

        // building all together
        popup.getContentPane().add(parentPanel);
        popup.pack(); // decides which size should be

        // place window below the parent component
        Point parentLocation = parent.getLocationOnScreen();
        int x = parentLocation.x + (parent.getWidth() / 2) - (width / 2); // centered by the parent component's middle
        int y = pxlsDown >= 0
                ? parentLocation.y + parent.getHeight() + pxlsDown // 5 pixels lower than the parent component
                : parentLocation.y + pxlsDown;

        if (mainWindow != null) {
            Point mainWindowLocation = mainWindow.getLocation();
            x = mainWindowLocation.x + mainWindow.getWidth() / 2 - (width / 2);
            y = mainWindowLocation.y + mainWindow.getHeight() / 2 - (height / 2);
        }

        popup.setLocation(x, y);

        // preparing before show the popup window
        popup.setOpacity(0F);
        popup.setVisible(true);

        // fade in
        int steps = 20;
        int delay = fadeDurationMs / steps;
        Timer fadeInTimer = new Timer(delay, null);
        fadeInTimer.addActionListener(new AbstractAction() {
            int step = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                float opacity = step / (float) steps;
                popup.setOpacity(Math.min(1f, opacity));
                if (step >= steps) {
                    fadeInTimer.stop();

                    // showing
                    new Timer(visibleDurationMs, new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ((Timer) e.getSource()).stop();

                            // fade out
                            int fadeOutSteps = 20;
                            int fadeOutDelay = fadeDurationMs / fadeOutSteps;
                            Timer fadeOutTimer = new Timer(fadeOutDelay, null);
                            fadeOutTimer.addActionListener(new AbstractAction() {
                                int outStep = 0;

                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    outStep++;
                                    float opacity = 1f - (outStep / (float) fadeOutSteps);
                                    popup.setOpacity(Math.max(0F, opacity));
                                    if (outStep >= fadeOutSteps) {
                                        popup.setVisible(false);
                                        popup.dispose();
                                        ((Timer) e.getSource()).stop();
                                        manager.showNext();
                                    }
                                }
                            });
                            fadeOutTimer.start();
                        }
                    }).start();
                }
            }
        });
        fadeInTimer.start();

    }
}
