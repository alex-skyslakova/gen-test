import pygame
import sys
from pygame.locals import *
from random import randint
import unittest
from unittest.mock import patch, Mock

# Assuming brownian_tree.py is in the same directory or PYTHONPATH
# from brownian_tree import Particle, MAXSPEED, WINDOWSIZE, SIZE, COLOR, freeParticles, tree, NEW, TICK, input

# If using a different import method, adjust accordingly.

class TestParticle(unittest.TestCase):

    def setUp(self):
        pygame.init()  # Initialize pygame for tests
        self.surface = pygame.Surface((WINDOWSIZE, WINDOWSIZE))  # Mock surface
        self.particle = Particle((5, 5), (100, 100), self.surface)


    def tearDown(self):
        pygame.quit()

    def test_init(self):
        self.assertEqual(self.particle.vector, (5, 5))
        self.assertEqual(self.particle.surface, self.surface)
        self.assertIn(self.particle, freeParticles)
        self.assertEqual(self.particle.rect, pygame.Rect(100, 100, SIZE, SIZE))

    def test_onEdge(self):
        # Test left edge
        self.particle.rect.left = -1
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[0], abs(self.particle.vector[0]))

        # Test top edge
        self.particle.rect.top = -1
        self.particle.vector = (5,-5) # Reset for test case
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[1], abs(self.particle.vector[1]))


        # Test right edge
        self.particle.rect.right = WINDOWSIZE + 1
        self.particle.vector = (5,5) # Reset for test case
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[0], -abs(self.particle.vector[0]))


        # Test bottom edge
        self.particle.rect.bottom = WINDOWSIZE + 1
        self.particle.vector = (5,5) # Reset for test case
        self.particle.onEdge()
        self.assertEqual(self.particle.vector[1], -abs(self.particle.vector[1]))

    @patch('brownian_tree.pygame.sprite.spritecollideany')
    @patch('brownian_tree.randint')
    def test_update_free_collision(self, mock_randint, mock_collide):
        mock_collide.side_effect = [True, False] # Collides with free particle, then no collision
        mock_randint.side_effect = [2, 3]
        self.particle.update()
        mock_collide.assert_called()
        mock_randint.assert_called()
        self.assertIn(self.particle, freeParticles) # added back to free particles after collision

    @patch('brownian_tree.pygame.sprite.spritecollideany')
    def test_update_tree_collision(self, mock_collide):
        mock_collide.side_effect = [False, True] # No free collision, then collides with tree
        self.particle.update()
        self.assertIn(self.particle, tree)
        self.assertNotIn(self.particle, freeParticles)
        self.assertEqual(self.particle.vector, (0, 0))



    def test_stop(self):
        self.particle.stop()
        self.assertEqual(self.particle.vector, (0, 0))
        self.assertNotIn(self.particle, freeParticles)
        self.assertIn(self.particle, tree)

    def test_accelerate(self):
        self.particle.accelerate((-3, 8))
        self.assertEqual(self.particle.vector, (-3, 8))



    @patch('brownian_tree.pygame.sprite.spritecollideany') # Mock the collision detection
    @patch('brownian_tree.randint', return_value = 5) # Mock random int generation for consistent test results
    def test_update_no_collision(self,mock_randint, mock_collide):
        mock_collide.return_value = False # Ensure no collisions

        initial_rect = self.particle.rect.copy()
        self.particle.update()
        self.assertEqual(self.particle.rect.topleft, (initial_rect.left + self.particle.vector[0], initial_rect.top + self.particle.vector[1]) ) 
        self.assertIn(self.particle, freeParticles)




class TestInput(unittest.TestCase):
    @patch('brownian_tree.sys.exit')
    def test_input_quit(self, mock_exit):
        input([pygame.event.Event(QUIT)])
        mock_exit.assert_called_with(0)

    @patch('brownian_tree.Particle')
    @patch('brownian_tree.randint', return_value = 50)
    def test_input_new_particle(self, mock_randint, mock_particle):
        input([pygame.event.Event(NEW)])
        mock_particle.assert_called()

    @patch('brownian_tree.freeParticles.update')
    def test_input_tick(self, mock_update):
        input([pygame.event.Event(TICK)])
        mock_update.assert_called()



if __name__ == '__main__':
    unittest.main()

