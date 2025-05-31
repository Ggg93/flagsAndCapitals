package dev.gl.flagsandcapitals.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author gl
 */
public class PopupWindow {

    private static int width = 120;
    private static int height = 30;

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
}
